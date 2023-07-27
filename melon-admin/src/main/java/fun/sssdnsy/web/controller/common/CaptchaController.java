package fun.sssdnsy.web.controller.common;

import com.google.code.kaptcha.Producer;
import fun.sssdnsy.config.Config;
import fun.sssdnsy.constant.CacheConstants;
import fun.sssdnsy.constant.Constants;
import fun.sssdnsy.core.domain.AjaxResult;
import fun.sssdnsy.service.ISysConfigService;
import fun.sssdnsy.utils.redis.RedisUtils;
import fun.sssdnsy.utils.sign.Base64;
import fun.sssdnsy.utils.uuid.IdUtils;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * 验证码操作处理
 *
 * @author sssdnsy
 */
@RestController
public class CaptchaController {
    @Resource(name = "captchaProducer")
    private Producer captchaProducer;

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Resource
    private ISysConfigService configService;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public AjaxResult getCode(HttpServletResponse response) throws IOException {
        AjaxResult ajax           = AjaxResult.success();
        boolean    captchaEnabled = configService.selectCaptchaEnabled();
        ajax.put("captchaEnabled", captchaEnabled);
        if (!captchaEnabled) {
            return ajax;
        }

        // 保存验证码信息
        String uuid      = IdUtils.getSuid();
        String verifyKey = CacheConstants.CAPTCHA_CODE_KEY + uuid;

        String        capStr = null, code = null;
        BufferedImage image  = null;

        // 生成验证码
        String captchaType = Config.getCaptchaType();
        if ("math".equals(captchaType)) {
            String capText = captchaProducerMath.createText();
            capStr = capText.substring(0, capText.lastIndexOf("@"));
            code = capText.substring(capText.lastIndexOf("@") + 1);
            image = captchaProducerMath.createImage(capStr);
        } else if ("char".equals(captchaType)) {
            capStr = code = captchaProducer.createText();
            image = captchaProducer.createImage(capStr);
        }

        RedisUtils.setCacheObject(verifyKey, code, Duration.of(Constants.CAPTCHA_EXPIRATION, ChronoUnit.MINUTES));
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }

        ajax.put("uuid", uuid);
        ajax.put("img", Base64.encode(os.toByteArray()));
        return ajax;
    }
}

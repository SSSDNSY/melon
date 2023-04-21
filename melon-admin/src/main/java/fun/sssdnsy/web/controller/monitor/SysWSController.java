package fun.sssdnsy.web.controller.monitor;

import fun.sssdnsy.utils.ip.IpUtils;
import fun.sssdnsy.web.domain.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.WebApplicationContextUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-04-21
 */
@CrossOrigin
@RestController
public class SysWSController {


    private final Logger logger = LoggerFactory.getLogger(SysWSController.class);

    @Autowired
    private SimpMessagingTemplate messageTemplate;

    private Server server = new Server();

    /**
     * 广播
     *
     * @param message
     * @return
     */
    @MessageMapping("/welcome")
    @SendTo("/topic/greetings")
    public Map say(Map message) {
        logger.info("{}@{}{}", "localhost"," ",message.get("chatType").toString());
        Map res = new HashMap();
        res.put("topic", message.get("chatType").toString());
        return res;
    }

    /**
     * 定时任务推送消息-（广播）
     *
     * @throws Exception
     */
    @Scheduled(fixedDelay = 1000)
    public void priceAutoConvert() throws Exception {
        server = new Server();
        server.copyTo();
        this.messageTemplate.convertAndSend("/topic/sysInfo", server);
        server=null;
    }

    /**
     * 请求接口推送消息-（广播）
     *
     * @param message
     */
    @GetMapping("/send")
    public void send(String message) {
        logger.info(message + "xxxviii");
        this.messageTemplate.convertAndSend("/queue/msg", message);
    }

    /**
     * 点对点通信
     *
     * @param message
     */
    @MessageMapping(value = "/point")
    @SendToUser("/topic/point")
    public String point(Map message) {
        logger.info(message.get("test") + "******");
        return "dd";
    }

    /**
     * 点对点通信
     *
     * @param message
     */
    @MessageMapping(value = "/points")
    public void point1(Map message) {
        logger.info(message.get("name") + "******");
        //发送消息给指定用户, 最后接受消息的路径会变成 /user/admin/queue/points
        messageTemplate.convertAndSendToUser("admin", "/queue/points", message);
    }


}

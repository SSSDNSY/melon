package fun.sssdnsy.melon.common.advice;

import fun.sssdnsy.melon.common.exception.BaseException;
import fun.sssdnsy.melon.pojo.dto.ResultDto;
import fun.sssdnsy.melon.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.lang.reflect.UndeclaredThrowableException;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.common.advice.ExceptionHandleAdvice
 * @desc
 * @since 2022-07-25
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandleAdvice {
    /**
     * MethodArgumentTypeMismatchException ： 方法参数类型异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    @ResponseBody
    public Object methodArgumentTypeMismatchException(Throwable e) {
        log.error("url参数异常，请检查参数类型是否匹配！", e);
        return ResultUtil.fail("url参数异常，请检查参数类型是否匹配");
    }

    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public ResultDto handle(Throwable e) {
        if (e instanceof BaseException) {
            return ResultUtil.fail(e.getMessage());
        }
        if (e instanceof UndeclaredThrowableException) {
            e = ((UndeclaredThrowableException) e).getUndeclaredThrowable();
        }
        log.error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), e);
        return ResultUtil.fail(e.getMessage());
    }

}

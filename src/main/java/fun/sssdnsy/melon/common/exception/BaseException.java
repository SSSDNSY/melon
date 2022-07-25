package fun.sssdnsy.melon.common.exception;

/**
 * @author fun.pengzh
 * @class fun.sssdnsy.melon.common.exception.BaseException
 * @desc 异常基类
 * @since 2022-07-25
 */
public class BaseException extends RuntimeException {

    public BaseException() {
        super();
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}

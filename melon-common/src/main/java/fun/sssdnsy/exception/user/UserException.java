package fun.sssdnsy.exception.user;

import fun.sssdnsy.exception.base.BaseException;

/**
 * 用户信息异常类
 *
 * @author sssdnsy
 */
public class UserException extends BaseException {
    private static final long serialVersionUID = 1L;

    public UserException(String code) {
        super("user", code, null, null);
    }
    public UserException(String code, Object[] args) {
        super("user", code, args, null);
    }
}

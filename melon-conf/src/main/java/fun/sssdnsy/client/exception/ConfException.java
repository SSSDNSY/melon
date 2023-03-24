package fun.sssdnsy.client.exception;

/**
 * @Desc
 * @Author pengzh
 * @Since 2023-03-24
 */
public class ConfException extends RuntimeException {

    private static final long serialVersionID = 54565646489654213L;

    public ConfException(String msg) {
        super(msg);
    }

    public ConfException(String msg, Throwable throwable) {
        super(msg, throwable);
    }

    public ConfException(Throwable throwable) {
        super(throwable);
    }

}

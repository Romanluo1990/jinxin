package roman.extramoney.jinxin.exception;

public class JinXinException extends Exception {

    private static final int DEFAULT_ERR_CODE = 500;

    private final int errCode;

    public JinXinException() {
        this(DEFAULT_ERR_CODE);
    }

    public JinXinException(int errCode) {
        this(errCode, (String) null);
    }

    public JinXinException(String message) {
        this(DEFAULT_ERR_CODE, message);
    }

    public JinXinException(int errCode, String message) {
        this(errCode, message, null);
    }

    public JinXinException(Throwable cause) {
        this(DEFAULT_ERR_CODE, cause);
    }

    public JinXinException(int errCode, Throwable cause) {
        this(errCode, (cause == null ? null : cause.toString()), cause);
    }

    public JinXinException(String message, Throwable cause) {
        this(DEFAULT_ERR_CODE, message, cause);
    }

    public JinXinException(int errCode, String message, Throwable cause) {
        this(errCode, message, cause, true, true);
    }

    public JinXinException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        this(DEFAULT_ERR_CODE, message, cause, enableSuppression, writableStackTrace);
    }

    public JinXinException(int errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public int getExceptionCode() {
        return errCode;
    }
}

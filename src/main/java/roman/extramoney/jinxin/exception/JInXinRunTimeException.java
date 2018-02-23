package roman.extramoney.jinxin.exception;

public class JInXinRunTimeException extends RuntimeException {

    private static final int DEFAULT_ERR_CODE = 500;

    private final int errCode;

    public JInXinRunTimeException() {
        this(DEFAULT_ERR_CODE);
    }

    public JInXinRunTimeException(int errCode) {
        this(errCode, (String) null);
    }

    public JInXinRunTimeException(String message) {
        this(DEFAULT_ERR_CODE, message);
    }

    public JInXinRunTimeException(int errCode, String message) {
        this(errCode, message, null);
    }

    public JInXinRunTimeException(Throwable cause) {
        this(DEFAULT_ERR_CODE, cause);
    }

    public JInXinRunTimeException(int errCode, Throwable cause) {
        this(errCode, (cause == null ? null : cause.toString()), cause);
    }

    public JInXinRunTimeException(String message, Throwable cause) {
        this(DEFAULT_ERR_CODE, message, cause);
    }

    public JInXinRunTimeException(int errCode, String message, Throwable cause) {
        this(errCode, message, cause, true, true);
    }

    public JInXinRunTimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        this(DEFAULT_ERR_CODE, message, cause, enableSuppression, writableStackTrace);
    }

    public JInXinRunTimeException(int errCode, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errCode = errCode;
    }

    public int getExceptionCode() {
        return errCode;
    }
}

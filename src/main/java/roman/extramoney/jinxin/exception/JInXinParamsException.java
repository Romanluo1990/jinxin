package roman.extramoney.jinxin.exception;

public class JInXinParamsException extends JInXinRunTimeException {

    private static final int EXCEPTION_CODE = 400;

    public JInXinParamsException(String message) {
        super(EXCEPTION_CODE, message);
    }

}

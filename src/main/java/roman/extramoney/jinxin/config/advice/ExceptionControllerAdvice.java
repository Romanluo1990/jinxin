package roman.extramoney.jinxin.config.advice;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import roman.extramoney.jinxin.controller.ApiResponse;
import roman.extramoney.jinxin.exception.JInXinParamsException;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.exception.JinXinException;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一处理controller类抛出的FinDcRuntimeException异常，转换成标准json输出格式信息
 * Created by jackie.yu on 2017/7/27.
 * 参考 springboot Error Handling
 */
@Slf4j
@ControllerAdvice(basePackages = "roman.extramoney.jinxin.controller")
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ResponseEntity handleControllerException(HttpServletRequest request, Throwable ex) {
        HttpStatus status = getStatus(request);
        int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();  // 默认500，服务器内部异常
        if (ex instanceof JInXinParamsException) {
            statusCode = ((JInXinParamsException) ex).getExceptionCode();
        }else if (ex instanceof JInXinRunTimeException) {
            statusCode = ((JInXinRunTimeException) ex).getExceptionCode();
        }else if (ex instanceof JinXinException) {
            statusCode = ((JinXinException) ex).getExceptionCode();
        }else if(ex instanceof UnauthorizedException){
            statusCode = 403;
        }
        log.error("handle error",ex);
        return new ResponseEntity(ApiResponse.failure(statusCode, ex.getMessage()), status);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.OK;
        }
        return HttpStatus.valueOf(statusCode);
    }

}

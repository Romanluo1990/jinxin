package roman.extramoney.jinxin.config.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import roman.extramoney.jinxin.controller.ApiResponse;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * 统一json返回处理
 */
@ControllerAdvice(basePackages = "roman.extramoney.jinxin.controller")
public class ResultJsonResponseAdvice implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType, Class selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            Class returnClass = (Class) returnType.getGenericParameterType();
            if (returnClass.isArray() || Collection.class.isAssignableFrom(returnClass)) {
                body = Collections.emptyList();
            } else if (String.class.equals(returnClass)) {
                body = "";
            } else {
                body = new HashMap<>();
            }
        }

        return ApiResponse.success(body);
    }

}

package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.BuildingRansom;

@Api("异常信息封装")
@RestController
public class ExceptionController {

    @RequestMapping("exception")
    public BuildingRansom getById(int errorCode,String message){
        throw new JInXinRunTimeException(errorCode,message);
    }

}

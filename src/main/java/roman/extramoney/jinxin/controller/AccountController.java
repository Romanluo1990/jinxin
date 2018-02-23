package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import roman.extramoney.jinxin.service.AccountService;
import roman.extramoney.jinxin.service.BridgeService;
import roman.extramoney.jinxin.service.WXService;

import javax.annotation.Resource;

@Api("用户接口")
@RequestMapping("account")
@RestController
public class AccountController extends BaseController<AccountService>{

    @Resource
    private WXService wxService;

    @RequestMapping(value = "login",method = RequestMethod.GET)
    @ApiOperation(value="登陆",notes = "通过小程序登陆返回的code登陆服务端")
    @ApiImplicitParam(name = "code", value = "小程序登陆code", required = true, dataType = "String", paramType = "query",example = "123456789")
    private void login(String code){
        wxService.login(code);
    }

    @RequestMapping(value = "register",method = RequestMethod.GET)
    @ApiOperation(value="注册",notes = "通过小程序登陆返回的code注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "小程序登陆code", required = true, dataType = "String", paramType = "query",example = "123456789"),
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, dataType = "String", paramType = "query",example = "张三")
    })
    private void register(String code, String nickName){
        wxService.register(code, nickName);
    }
}

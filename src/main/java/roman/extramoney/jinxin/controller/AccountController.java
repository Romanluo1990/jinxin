package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import roman.extramoney.jinxin.config.shiro.AccountDto;
import roman.extramoney.jinxin.config.shiro.WxRealm;
import roman.extramoney.jinxin.config.shiro.WxToken;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.service.AccountService;
import roman.extramoney.jinxin.service.BridgeService;
import roman.extramoney.jinxin.service.WXService;

import javax.annotation.Resource;
import java.util.List;

@Api("用户接口")
@RequestMapping("account")
@RestController
public class AccountController extends BaseController<AccountService>{

    @Resource
    private WXService wxService;

    @Resource
    private WxRealm wxRealm;

    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value="登陆",notes = "通过小程序登陆返回的code登陆服务端")
    @ApiImplicitParam(name = "code", value = "小程序登陆code", required = true, dataType = "String", paramType = "form",example = "123456789")
    private AccountDto login(String code){
        Subject subject = SecurityUtils.getSubject();
        try{
            subject.login(new WxToken(code));
        }catch (AuthenticationException e){
            if(e.getCause() instanceof JInXinRunTimeException)
                throw (JInXinRunTimeException)e.getCause();
            throw new JInXinRunTimeException(402,"微信登陆失败");
        }
        return (AccountDto)subject.getPrincipal();
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="注册",notes = "通过小程序登陆返回的code注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "小程序登陆code", required = true, dataType = "String", paramType = "form",example = "123456789"),
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, dataType = "String", paramType = "form",example = "张三"),
            @ApiImplicitParam(name = "image", value = "用户头像", required = true, dataType = "String", paramType = "form",example = "url"),
            @ApiImplicitParam(name = "type", value = "用户类型（1：客户经理，2：地产中介，3：替他，4：内部人员）", allowableValues = "1,2,3,4",required = true, dataType = "int", paramType = "form",example = "123456789"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = false, dataType = "String", paramType = "form",example = "18888888888")
    })
    private void register(String code, String nickName, String image,int type,@RequestParam(required = false) String phone){
        wxService.register(code, nickName,image,type,phone);
    }

    @RequestMapping(value = "image/update")
    private void updateImage(long accountId,String image){
        service.updateImage(accountId,image);
    }


    @RequestMapping(value = "permission/list",method = RequestMethod.GET)
    @ApiOperation(value="获取用户权限",notes = "获取用户权限")
    private List<String> listPermissions(){
        Subject subject = SecurityUtils.getSubject();
        AccountDto accountDto = (AccountDto) subject.getPrincipal();
        return accountDto.getPermissions();
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ApiOperation(value="退出登陆")
    private void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}

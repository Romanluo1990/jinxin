package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.config.shiro.AccountDto;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.*;
import roman.extramoney.jinxin.service.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Api("管理员接口")
@RequestMapping("admin")
@RestController
public class AdminController{

    @Resource
    private AccountService accountService;

    @Resource
    private BridgeService bridgeService;

    @Resource
    private BuildingPropertyService buildingPropertyService;

    @Resource
    private BuildingRansomService buildingRansomService;

    @Resource
    private OtherLoansService otherLoansService;

    @Resource
    private ForumPostService forumPostService;

    @Resource
    private ForumReplyService forumReplyService;

    @Resource
    private SysConfigService sysConfigService;


    @RequestMapping(value = "login",method = RequestMethod.POST)
    @ApiOperation(value="登陆",notes = "通过小程序登陆返回的code登陆服务端")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "form", example = "admin"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form", example = "123456789")
    })
    public AccountDto login(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token);
        } catch (AuthenticationException ice) {
            // 捕获密码错误异常
            throw new JInXinRunTimeException(402,"账号密码错误");
        }
        return (AccountDto)subject.getPrincipal();
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "bridge/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取过桥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通过）", allowableValues ="0,1,2" ,required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<Bridge> bridgePage(@RequestParam(required = false) Integer status,@RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return bridgeService.page(status,fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "buildingProperty/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取物业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通过）", allowableValues ="0,1,2" ,required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<BuildingProperty> buildingPropertyPage(@RequestParam(required = false) Integer status, @RequestParam(required = false) Date fromDate,
                                              @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return buildingPropertyService.page(status,fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "buildingRansom/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取赎楼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通过）", allowableValues ="0,1,2" ,required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<BuildingRansom> buildingRansomPage(@RequestParam(required = false) Integer status, @RequestParam(required = false) Date fromDate,
                                                      @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return buildingRansomService.page(status,fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "otherLoans/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取其他")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通过）", allowableValues ="0,1,2" ,required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<OtherLoans> otherLoansPage(@RequestParam(required = false) Integer status, @RequestParam(required = false) Date fromDate,
                                                @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return otherLoansService.page(status,fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "forumPost/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<ForumPost> forumPostPage(@RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return forumPostService.page(fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "forumReply/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取帖子回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<ForumReply> forumReplyPage(@RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return forumReplyService.page(fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "account/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通过）", allowableValues ="0,1,2" ,required = false, dataType = "int", paramType = "query",example = "0"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<AccountDto> accountPage(@RequestParam(required = false) Integer status, @RequestParam(required = false) Date fromDate,
                                           @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return accountService.page(status,fromDate,toDate,pageNum,pageSize);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "bridge/audit",method = RequestMethod.POST)
    @ApiOperation(value="审核过桥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "过桥id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "status", value = "更新状态", allowableValues = "1,2", required = true, dataType = "int", paramType = "form",example = "1")
    })
    public void bridgeAudit(long id,int status){
        bridgeService.audit(id,status);
    }


    @RequiresPermissions("admin")
    @RequestMapping(value = "buildingRansom/audit",method = RequestMethod.POST)
    @ApiOperation(value="审核赎楼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "赎楼id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "status", value = "更新状态", allowableValues = "1,2", required = true, dataType = "int", paramType = "form",example = "1")
    })
    public void buildingRansomAudit(long id,int status){
        buildingRansomService.audit(id,status);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "buildingProperty/audit",method = RequestMethod.POST)
    @ApiOperation(value="审核物业")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "物业id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "status", value = "更新状态", allowableValues = "1,2", required = true, dataType = "int", paramType = "form",example = "1")
    })
    public void buildingPropertyAudit(long id,int status){
        buildingPropertyService.audit(id,status);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "otherLoans/audit",method = RequestMethod.POST)
    @ApiOperation(value="审核其他借贷")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "其他借贷id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "status", value = "更新状态", allowableValues = "1,2", required = true, dataType = "int", paramType = "form",example = "1")
    })
    public void otherLoansAudit(long id,int status){
        otherLoansService.audit(id,status);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "account/audit",method = RequestMethod.POST)
    @ApiOperation(value="审核用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "status", value = "更新状态", allowableValues = "1,2", required = true, dataType = "int", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "permissions", value = "权限", required = false, dataType = "string", paramType = "form",example = "admin,bridge")
    })
    public void accountAudit(long id,int status,@RequestParam(required = false) String permissions){
        accountService.audit(id,status,permissions);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "permissions/update",method = RequestMethod.POST)
    @ApiOperation(value="权限更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户id", required = true, dataType = "long", paramType = "form",example = "1"),
            @ApiImplicitParam(name = "permissions", value = "权限", required = false, dataType = "string", paramType = "form",example = "admin,bridge")
    })
    public void permissionUpdate(long accountId, String permissions){
        accountService.permissionUpdate(accountId,permissions);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "forumPost",method = RequestMethod.DELETE)
    @ApiOperation(value="删除贴子")
    @ApiImplicitParam(name = "id", value = "帖子id", required = true, dataType = "long", paramType = "form",example = "1")
    public void forumPostDelete(long id){
        forumPostService.delete(id);
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "forumReply",method = RequestMethod.DELETE)
    @ApiOperation(value="删除回复")
    @ApiImplicitParam(name = "id", value = "回复id", required = true, dataType = "long", paramType = "form",example = "1")
    public void forumReplyDelete(long id){
        forumReplyService.delete(id);
    }

    @RequestMapping(value = "register",method = RequestMethod.POST)
    @ApiOperation(value="注册",notes = "账号密码注册")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "nickName", value = "用户昵称", required = true, dataType = "String", paramType = "form",example = "123456789"),
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "form",example = "张三"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "form",example = "123456789"),
            @ApiImplicitParam(name = "phone", value = "联系电话", required = false, dataType = "String", paramType = "form",example = "18888888888")
    })
    public void register(String nickName,String userName,String password, @RequestParam(required = false) String phone){
        accountService.register(nickName,userName,password,phone);
    }

    @RequestMapping(value = "logout",method = RequestMethod.GET)
    @ApiOperation(value="退出登陆")
    public void logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

    @RequiresPermissions("admin")
    @RequestMapping(value = "sysConfid/page",method = RequestMethod.GET)
    @ApiOperation(value="分页获取系统配置")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<SysConfig> sysConfigPage(@RequestParam(required = false) Integer status, @RequestParam(required = false) Date fromDate,
                                        @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return sysConfigService.page(pageNum,pageSize);
    }

    @RequestMapping(value = "sysConfig/saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新系统配置",notes="无ID保存，有ID更新")
    public SysConfig saveOrUpdateSysConfig(@RequestBody SysConfig sysConfig){
        sysConfigService.saveOrUpdate(sysConfig);
        return sysConfig;
    }
}

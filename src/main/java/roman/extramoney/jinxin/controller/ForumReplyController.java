package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.ForumReply;
import roman.extramoney.jinxin.model.ForumReplyWithUser;
import roman.extramoney.jinxin.service.AccountService;
import roman.extramoney.jinxin.service.ForumReplyService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Api("帖子回复信息接口")
@RequestMapping("forumReply")
@RestController
public class ForumReplyController extends BaseController<ForumReplyService>{

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取帖子回复详情",notes = "根据ID获取帖子回复信息")
    @ApiImplicitParam(name = "id", value = "帖子回复ID", required = true, dataType = "long", paramType = "path",example = "1")
    private ForumReply getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新帖子回复详情",notes="无ID保存，有ID更新")
    private ForumReply saveOrUpdate(@RequestBody ForumReply forumReply){
        service.saveOrUpdate(forumReply);
        return forumReply;
    }

    @RequestMapping(value = "account/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户分页获取帖子回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    private List<ForumReply> pageByAccountId(long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    @RequestMapping(value = "post_{postId}/page",method = RequestMethod.GET)
    @ApiOperation(value="根据帖子ID分页获取帖子回复")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "ID", required = true, dataType = "long", paramType = "path",example = "1"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<ForumReplyWithUser> pageByPostId(@PathVariable long postId, @RequestParam(required = false) Date fromDate,
                                                  @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByPostId(postId,fromDate,toDate,pageNum,pageSize).stream().map(forumReply->{
            Account account = accountService.getById(forumReply.getAccountId());
            return new ForumReplyWithUser(account.getNickName(),account.getImage(),forumReply);
        }).collect(Collectors.toList());
    }
}

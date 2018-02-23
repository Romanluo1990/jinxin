package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.ForumPost;
import roman.extramoney.jinxin.service.ForumPostService;

import java.util.Date;
import java.util.List;

@Api("帖子信息接口")
@RequestMapping("forumPost")
@RestController
public class ForumPostController extends BaseController<ForumPostService>{

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取帖子详情",notes = "根据ID获取帖子信息")
    @ApiImplicitParam(name = "id", value = "帖子ID", required = true, dataType = "long", paramType = "path",example = "1")
    private ForumPost getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新帖子详情",notes="无ID保存，有ID更新")
    @ApiImplicitParam(name = "ForumPost", value = "帖子信息", required = true, dataType = "roman.extramoney.jinxin.model.ForumPost", paramType = "body")
    private ForumPost saveOrUpdate(@RequestBody ForumPost ForumPost){
        service.saveOrUpdate(ForumPost);
        return ForumPost;
    }

    @RequestMapping(value = "account_{accountId}/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户分页获取帖子")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户ID", required = true, dataType = "long", paramType = "path",example = "1"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    private List<ForumPost> pageByAccountId(@PathVariable long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }
}

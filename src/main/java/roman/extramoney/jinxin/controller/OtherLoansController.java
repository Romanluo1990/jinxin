package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.OtherLoans;
import roman.extramoney.jinxin.service.OtherLoansService;

import java.util.Date;
import java.util.List;

@RequiresPermissions("otherLoans")
@Api("其他借款信息接口")
@RequestMapping("otherLoans")
@RestController
public class OtherLoansController extends BaseController<OtherLoansService>{

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取其他借款详情",notes = "根据ID获取其他借款信息")
    @ApiImplicitParam(name = "id", value = "其他借款ID", required = true, dataType = "long", paramType = "path",example = "1")
    public OtherLoans getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新其他借款详情",notes="无ID保存，有ID更新")
    public OtherLoans saveOrUpdate(@RequestBody OtherLoans otherLoans){
        service.saveOrUpdate(otherLoans);
        return otherLoans;
    }

    @RequestMapping(value = "account/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户ID分页获取其他借款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<OtherLoans> pageByAccountId(long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }
}

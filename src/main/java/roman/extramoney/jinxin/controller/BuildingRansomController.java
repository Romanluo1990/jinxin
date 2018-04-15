package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.service.BuildingRansomService;

import java.util.Date;
import java.util.List;

@RequiresPermissions("buildingRanSom")
@Api("赎楼信息接口")
@RequestMapping("buildingRanSom")
@RestController
public class BuildingRansomController extends BaseController<BuildingRansomService>{

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取赎楼详情",notes = "根据ID获取赎楼信息")
    @ApiImplicitParam(name = "id", value = "赎楼ID", required = true, dataType = "long", paramType = "path",example = "1")
    public BuildingRansom getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新赎楼详情",notes="无ID保存，有ID更新")
    public BuildingRansom saveOrUpdate(@RequestBody BuildingRansom buildingRansom){
        service.saveOrUpdate(buildingRansom);
        return buildingRansom;
    }

    @RequestMapping(value = "account/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户分页获取赎楼")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "status", value = "状态（0:待审核 1:审核通过 2:审核不通)", required = false, dataType = "int",allowableValues = "0,1,2",paramType = "query",example = "0"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<BuildingRansom> pageByAccountId(long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate,
                                                @RequestParam(required = false) Integer status, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,status,pageNum,pageSize);
    }
}

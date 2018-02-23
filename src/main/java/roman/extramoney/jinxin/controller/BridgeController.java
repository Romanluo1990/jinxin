package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.service.BaseService;
import roman.extramoney.jinxin.service.BridgeService;

import java.math.BigDecimal;
import java.util.*;

@Api("过桥信息接口")
@RequestMapping("bridge")
@RestController
public class BridgeController extends BaseController<BridgeService>{

    private static final BigDecimal AMOUNT_AVAILABLE = new BigDecimal("1000");

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取过桥详情",notes = "根据ID获取过桥信息")
    @ApiImplicitParam(name = "id", value = "过桥ID", required = true, dataType = "long", paramType = "path",example = "1")
    private Bridge getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新过桥详情",notes="无ID保存，有ID更新")
    @ApiImplicitParam(name = "bridge", value = "过桥信息", required = true, dataType = "roman.extramoney.jinxin.model.Bridge", paramType = "body")
    private Bridge saveOrUpdate(@RequestBody Bridge bridge){
        service.saveOrUpdate(bridge);
        return bridge;
    }

    @RequestMapping(value = "account_{accountId}/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户分页获取过桥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "用户ID", required = true, dataType = "long", paramType = "path",example = "1"),
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    private List<Bridge> pageByAccountId(@PathVariable long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    @RequestMapping(value = "balance",method = RequestMethod.GET)
    @ApiOperation(value="根据日前获取可用余额")
    @ApiImplicitParam(name = "dates", value = "日期，格式yyyyMMdd，逗号分开", required = true, dataType = "String", paramType = "query",example = "20180101,20180102")
    private Map<String,BigDecimal> balance(@RequestParam String dates){
        Map<String,BigDecimal> blanceMap = new HashMap<>();
        Arrays.stream(dates.split(","))
                .forEach(date -> {
                    List<Bridge> bridges = service.listByDate(date);
                    BigDecimal totalAmount = bridges.stream()
                            .map(Bridge::getAmount)
                            .reduce(BigDecimal.ZERO,(x,y)->x.add(y));
                    BigDecimal blance = AMOUNT_AVAILABLE.subtract(totalAmount);
                    blanceMap.put(date,blance);
                });
        return blanceMap;
    }
}

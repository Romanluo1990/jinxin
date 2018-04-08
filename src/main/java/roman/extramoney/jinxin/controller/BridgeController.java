package roman.extramoney.jinxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.service.BaseService;
import roman.extramoney.jinxin.service.BridgeService;
import roman.extramoney.jinxin.service.SysConfigService;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

@RequiresPermissions("bridge")
@Api("过桥信息接口")
@RequestMapping("bridge")
@RestController
public class BridgeController extends BaseController<BridgeService>{

    @Resource
    private SysConfigService sysConfigService;

    @RequestMapping(value = "get/{id}",method = RequestMethod.GET)
    @ApiOperation(value="获取过桥详情",notes = "根据ID获取过桥信息")
    @ApiImplicitParam(name = "id", value = "过桥ID", required = true, dataType = "long", paramType = "path",example = "1")
    public Bridge getById(@PathVariable long id){
        return service.getById(id);
    }

    @RequestMapping(value = "saveOrUpdate",method = RequestMethod.POST)
    @ApiOperation(value="保存或更新过桥详情",notes="无ID保存，有ID更新")
    public Bridge saveOrUpdate(@RequestBody Bridge bridge){
        service.saveOrUpdate(bridge);
        return bridge;
    }

    @RequestMapping(value = "account/page",method = RequestMethod.GET)
    @ApiOperation(value="根据用户分页获取过桥")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fromDate", value = "起始时间", required = false, dataType = "ljava.util.Date", paramType = "query",example = "2018-01-01 00:00:00"),
            @ApiImplicitParam(name = "toDate", value = "结束时间", required = false, dataType = "java.util.Date", paramType = "query",example = "2018-01-02 00:00:00"),
            @ApiImplicitParam(name = "pageNum", value = "页码", required = true, dataType = "int", paramType = "query",example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "条数", required = true, dataType = "int", paramType = "query",example = "10")})
    public List<Bridge> pageByAccountId( long accountId, @RequestParam(required = false) Date fromDate,
                                         @RequestParam(required = false) Date toDate, int pageNum, int pageSize){
        return service.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    @RequestMapping(value = "balance",method = RequestMethod.GET)
    @ApiOperation(value="根据日前获取可用余额")
    @ApiImplicitParam(name = "dates", value = "日期，格式yyyyMMdd，逗号分开", required = true, dataType = "String", paramType = "query",example = "20180101,20180102")
    public Map<String,BigDecimal> balance(@RequestParam String dates){
        Map<String,BigDecimal> blanceMap = new HashMap<>();
        BigDecimal amountAvailable = new BigDecimal(sysConfigService.getByKey("amountAvailable").getValue());
        Arrays.stream(dates.split(","))
                .forEach(date -> {
                    List<Bridge> bridges = service.listByDate(date);
                    BigDecimal totalAmount = bridges.stream()
                            .map(Bridge::getAmount)
                            .reduce(BigDecimal.ZERO,(x,y)->x.add(y));
                    BigDecimal blance = amountAvailable.subtract(totalAmount);
                    blanceMap.put(date,blance);
                });
        return blanceMap;
    }

    @RequestMapping(value = "{month}/balance",method = RequestMethod.GET)
    @ApiOperation(value="根据日前获取可用余额")
    @ApiImplicitParam(name = "month", value = "月份", required = true, dataType = "String", paramType = "path",example = "201801")
    public Map<String,BigDecimal> monthBalance(@PathVariable String month) throws ParseException {
        Date date = DateUtils.parseDate(month,"yyyyMM");
        Date endDate = DateUtils.addMonths(date,1);
        BigDecimal amountAvailable = new BigDecimal(sysConfigService.getByKey("amountAvailable").getValue());
        Map<String,BigDecimal> blanceMap = new HashMap<>();
        while (true){
            List<Bridge> bridges = service.listByDate(date);
            BigDecimal totalAmount = bridges.stream()
                    .map(Bridge::getAmount)
                    .reduce(BigDecimal.ZERO,(x,y)->x.add(y));
            BigDecimal blance = amountAvailable.subtract(totalAmount);
            blanceMap.put(DateFormatUtils.format(date,"yyyyMMdd"),blance);
            date = DateUtils.addDays(date,1);
            if(date.compareTo(endDate)>=0)
                break;
        }
        return blanceMap;
    }
}

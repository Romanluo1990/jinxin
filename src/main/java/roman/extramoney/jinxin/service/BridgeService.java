package roman.extramoney.jinxin.service;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roman.extramoney.jinxin.dao.BridgeDao;
import roman.extramoney.jinxin.dao.impl.BridgeDaoImpl;
import roman.extramoney.jinxin.model.Bridge;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class BridgeService extends BaseService<BridgeDao,Bridge>{

    @Override
    public void save(Bridge bridge) {
        bridge.setPlannedRepaymentTime(DateUtils.addDays(bridge.getExpectedAccountTime(),bridge.getDays()));
        super.save(bridge);
    }

    public List<Bridge> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    public List<Bridge> listByDate(String dateString) {
        Date date = null;
        try {
            date = DateUtils.parseDate(dateString,"yyyyMMdd");
            return listByDate(date);
        } catch (ParseException e) {
            return Collections.emptyList();
        }
    }


    public List<Bridge> listByDate(Date date) {
        return dao.listByDate(date);
    }

    public List<Bridge> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.page(status,fromDate,toDate,pageNum,pageSize);
    }

    public void audit(long id, int status) {
        Bridge bridge = new Bridge();
        bridge.setId(id);
        bridge.setStatus(status);
        dao.updateByIdSelective(bridge);
    }

}

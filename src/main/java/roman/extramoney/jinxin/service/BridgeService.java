package roman.extramoney.jinxin.service;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.BridgeDao;
import roman.extramoney.jinxin.dao.impl.BridgeDaoImpl;
import roman.extramoney.jinxin.model.Bridge;

import java.text.ParseException;
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
            return dao.listByDate(date);
        } catch (ParseException e) {
            return Collections.emptyList();
        }

    }
}

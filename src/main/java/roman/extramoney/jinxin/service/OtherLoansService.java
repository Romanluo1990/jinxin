package roman.extramoney.jinxin.service;

import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.OtherLoansDao;
import roman.extramoney.jinxin.dao.impl.OtherLoansDaoImpl;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.OtherLoans;

import java.util.Date;
import java.util.List;

@Service
public class OtherLoansService extends BaseService<OtherLoansDao,OtherLoans>{

    public List<OtherLoans> pageByAccountId(long accountId, Date fromDate, Date toDate, Integer status, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,status,pageNum,pageSize);
    }

    public PageInfo<OtherLoans> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return new PageInfo<>(dao.page(status,fromDate,toDate,pageNum,pageSize));
    }

    public void audit(long id, int status, String statusMessage) {
        OtherLoans otherLoans = new OtherLoans();
        otherLoans.setId(id);
        otherLoans.setStatus(status);
        otherLoans.setStatusMessage(statusMessage);
        dao.updateByIdSelective(otherLoans);
    }
}

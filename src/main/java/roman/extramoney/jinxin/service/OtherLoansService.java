package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.OtherLoansDao;
import roman.extramoney.jinxin.dao.impl.OtherLoansDaoImpl;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.OtherLoans;

import java.util.Date;
import java.util.List;

@Service
public class OtherLoansService extends BaseService<OtherLoansDao,OtherLoans>{

    public List<OtherLoans> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    public List<OtherLoans> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.page(status,fromDate,toDate,pageNum,pageSize);
    }

    public void audit(long id, int status) {
        OtherLoans otherLoans = new OtherLoans();
        otherLoans.setId(id);
        otherLoans.setStatus(status);
        dao.updateByIdSelective(otherLoans);
    }
}

package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.ForumReply;
import roman.extramoney.jinxin.model.OtherLoans;

import java.util.Date;
import java.util.List;

public interface OtherLoansDao extends BaseDao<OtherLoans>{

    List<OtherLoans> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);

}

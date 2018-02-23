package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.Bridge;

import java.util.Date;
import java.util.List;

public interface BridgeDao extends BaseDao<Bridge>{

    List<Bridge> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);

    List<Bridge> listByDate(Date date);
}

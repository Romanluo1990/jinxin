package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.BuildingProperty;
import roman.extramoney.jinxin.model.BuildingRansom;

import java.util.Date;
import java.util.List;

public interface BuildingRansomDao extends BaseDao<BuildingRansom>{

    List<BuildingRansom> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);

    List<BuildingRansom> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize);
}

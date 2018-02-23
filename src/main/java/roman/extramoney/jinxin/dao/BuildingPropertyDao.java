package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingProperty;

import java.util.Date;
import java.util.List;

public interface BuildingPropertyDao extends BaseDao<BuildingProperty>{

    List<BuildingProperty> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);
}

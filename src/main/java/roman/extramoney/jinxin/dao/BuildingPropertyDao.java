package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.BuildingProperty;

import java.util.Date;
import java.util.List;

public interface BuildingPropertyDao extends BaseDao<BuildingProperty>{

    List<BuildingProperty> pageByAccountId(long accountId, Date fromDate, Date toDate, Integer status, int pageNum, int pageSize);

    List<BuildingProperty> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize);
}

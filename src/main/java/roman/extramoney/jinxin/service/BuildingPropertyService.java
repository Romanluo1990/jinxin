package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.BuildingPropertyDao;
import roman.extramoney.jinxin.dao.impl.BuildingPropertyDaoImpl;
import roman.extramoney.jinxin.model.BuildingProperty;

import java.util.Date;
import java.util.List;

@Service
public class BuildingPropertyService extends BaseService<BuildingPropertyDao,BuildingProperty>{

    public List<BuildingProperty> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }
}

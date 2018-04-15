package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.BuildingPropertyDao;
import roman.extramoney.jinxin.dao.impl.BuildingPropertyDaoImpl;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingProperty;

import java.util.Date;
import java.util.List;

@Service
public class BuildingPropertyService extends BaseService<BuildingPropertyDao,BuildingProperty>{

    public List<BuildingProperty> pageByAccountId(long accountId, Date fromDate, Date toDate, Integer status, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,status,pageNum,pageSize);
    }

    public List<BuildingProperty> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.page(status,fromDate,toDate,pageNum,pageSize);
    }

    public void audit(long id, int status) {
        BuildingProperty buildingProperty = new BuildingProperty();
        buildingProperty.setId(id);
        buildingProperty.setStatus(status);
        dao.updateByIdSelective(buildingProperty);
    }
}

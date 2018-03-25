package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.BuildingRansomDao;
import roman.extramoney.jinxin.dao.impl.BuildingRansomDaoImpl;
import roman.extramoney.jinxin.model.BuildingProperty;
import roman.extramoney.jinxin.model.BuildingRansom;

import java.util.Date;
import java.util.List;

@Service
public class BuildingRansomService extends BaseService<BuildingRansomDao,BuildingRansom>{

    public List<BuildingRansom> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    public List<BuildingRansom> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.page(status,fromDate,toDate,pageNum,pageSize);
    }

    public void audit(long id, int status) {
        BuildingRansom buildingRansom = new BuildingRansom();
        buildingRansom.setId(id);
        buildingRansom.setStatus(status);
        dao.updateByIdSelective(buildingRansom);
    }
}

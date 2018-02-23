package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.BuildingRansomDao;
import roman.extramoney.jinxin.dao.mapper.BuildingRansomMapper;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingProperty;
import roman.extramoney.jinxin.model.BuildingRansom;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class BuildingRansomDaoImpl extends BaseDaoImpl<BuildingRansomMapper,BuildingRansom> implements BuildingRansomDao {

    @Override
    public List<BuildingRansom> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(BuildingRansom.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("accountId", accountId);
        if(fromDate != null){
            criteria.andGreaterThanOrEqualTo("createTime",fromDate);
        }
        if(toDate != null){
            criteria.andLessThanOrEqualTo("createTime",toDate);
        }
        PageHelper.startPage(pageNum,pageSize,false);
        PageHelper.orderBy("update_time desc");
        return mapper.selectByExample(example);
    }
}

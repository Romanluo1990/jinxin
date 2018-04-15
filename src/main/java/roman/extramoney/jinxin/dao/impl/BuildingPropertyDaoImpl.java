package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.BuildingPropertyDao;
import roman.extramoney.jinxin.dao.mapper.BuildingPropertyMapper;
import roman.extramoney.jinxin.model.BuildingProperty;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class BuildingPropertyDaoImpl extends BaseDaoImpl<BuildingPropertyMapper,BuildingProperty> implements BuildingPropertyDao {

    @Override
    public List<BuildingProperty> pageByAccountId(long accountId, Date fromDate, Date toDate, Integer status, int pageNum, int pageSize) {
        Example example = new Example(BuildingProperty.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("accountId", accountId);
        if(fromDate != null){
            criteria.andGreaterThanOrEqualTo("createTime",fromDate);
        }
        if(toDate != null){
            criteria.andLessThanOrEqualTo("createTime",toDate);
        }
        if(status != null){
            criteria.andEqualTo("status",status);
        }
        PageHelper.startPage(pageNum,pageSize,false);
        PageHelper.orderBy("update_time desc");
        return mapper.selectByExample(example);
    }

    @Override
    public List<BuildingProperty> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(BuildingProperty.class);
        Example.Criteria criteria = example.createCriteria();
        if(status != null){
            criteria.andEqualTo("status",status);
        }
        if(fromDate != null){
            criteria.andGreaterThanOrEqualTo("createTime",fromDate);
        }
        if(toDate != null){
            criteria.andLessThanOrEqualTo("createTime",toDate);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("update_time desc");
        return mapper.selectByExample(example);
    }
}

package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.BridgeDao;
import roman.extramoney.jinxin.dao.mapper.BridgeMapper;
import roman.extramoney.jinxin.model.Bridge;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class BridgeDaoImpl extends BaseDaoImpl<BridgeMapper,Bridge> implements BridgeDao {

    @Override
    public List<Bridge> pageByAccountId(long accountId, Date fromDate, Date toDate, Integer status, int pageNum, int pageSize) {
        Example example = new Example(Bridge.class);
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
    public List<Bridge> listByDate(Date date) {
        Example example = new Example(Bridge.class);
        example.createCriteria()
                .andGreaterThanOrEqualTo("plannedRepaymentTime",date)
                .andLessThanOrEqualTo("expectedAccountTime",date)
                .andIn("status", Arrays.asList(0,1));
        return mapper.selectByExample(example);
    }

    @Override
    public List<Bridge> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(Bridge.class);
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

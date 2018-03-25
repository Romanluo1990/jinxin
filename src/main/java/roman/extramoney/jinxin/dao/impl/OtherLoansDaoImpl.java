package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.OtherLoansDao;
import roman.extramoney.jinxin.dao.mapper.OtherLoansMapper;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.OtherLoans;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class OtherLoansDaoImpl extends BaseDaoImpl<OtherLoansMapper,OtherLoans> implements OtherLoansDao {

    @Override
    public List<OtherLoans> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(OtherLoans.class);
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

    @Override
    public List<OtherLoans> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(OtherLoans.class);
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
        PageHelper.startPage(pageNum,pageSize,false);
        PageHelper.orderBy("update_time desc");
        return mapper.selectByExample(example);
    }


}

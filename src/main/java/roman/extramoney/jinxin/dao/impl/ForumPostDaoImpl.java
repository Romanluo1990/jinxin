package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.ForumPostDao;
import roman.extramoney.jinxin.dao.mapper.ForumPostMapper;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.ForumPost;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class ForumPostDaoImpl extends BaseDaoImpl<ForumPostMapper,ForumPost> implements ForumPostDao {

    @Override
    public List<ForumPost> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(ForumPost.class);
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
    public List<ForumPost> page(Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(ForumPost.class);
        Example.Criteria criteria = example.createCriteria();
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

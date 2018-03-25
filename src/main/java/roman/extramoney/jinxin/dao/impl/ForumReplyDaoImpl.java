package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.ForumReplyDao;
import roman.extramoney.jinxin.dao.mapper.ForumReplyMapper;
import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.BuildingRansom;
import roman.extramoney.jinxin.model.ForumReply;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class ForumReplyDaoImpl extends BaseDaoImpl<ForumReplyMapper,ForumReply> implements ForumReplyDao {

    @Override
    public List<ForumReply> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(ForumReply.class);
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
    public List<ForumReply> pageByPostId(long postId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(ForumReply.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("postId", postId);
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
    public List<ForumReply> page(Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(ForumReply.class);
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

package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.ForumReplyDao;
import roman.extramoney.jinxin.dao.impl.ForumReplyDaoImpl;
import roman.extramoney.jinxin.model.ForumReply;

import java.util.Date;
import java.util.List;

@Service
public class ForumReplyService extends BaseService<ForumReplyDao,ForumReply>{

    public List<ForumReply> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }

    public List<ForumReply> pageByPostId(long postId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByPostId(postId,fromDate,toDate,pageNum,pageSize);
    }

    public List<ForumReply> page(Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.page(fromDate,toDate,pageNum,pageSize);
    }

}

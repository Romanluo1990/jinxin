package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.ForumPostDao;
import roman.extramoney.jinxin.dao.impl.ForumPostDaoImpl;
import roman.extramoney.jinxin.model.ForumPost;

import java.util.Date;
import java.util.List;

@Service
public class ForumPostService extends BaseService<ForumPostDao,ForumPost>{

    public List<ForumPost> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize) {
        return dao.pageByAccountId(accountId,fromDate,toDate,pageNum,pageSize);
    }
}

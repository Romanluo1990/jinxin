package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.ForumPost;
import roman.extramoney.jinxin.model.ForumReply;

import java.util.Date;
import java.util.List;

public interface ForumReplyDao extends BaseDao<ForumReply>{

    List<ForumReply> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);

    List<ForumReply> pageByPostId(long postId, Date fromDate, Date toDate, int pageNum, int pageSize);
}

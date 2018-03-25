package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Bridge;
import roman.extramoney.jinxin.model.ForumPost;

import java.util.Date;
import java.util.List;

public interface ForumPostDao extends BaseDao<ForumPost>{

    List<ForumPost> pageByAccountId(long accountId, Date fromDate, Date toDate, int pageNum, int pageSize);

    List<ForumPost> page(Date fromDate, Date toDate, int pageNum, int pageSize);
}

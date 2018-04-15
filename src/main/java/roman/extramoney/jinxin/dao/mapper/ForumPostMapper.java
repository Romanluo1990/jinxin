package roman.extramoney.jinxin.dao.mapper;

import roman.extramoney.jinxin.model.ForumPost;
import tk.mybatis.mapper.common.Mapper;

public interface ForumPostMapper extends Mapper<ForumPost> {
    void incrementTimes(long id);
}
package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.SysConfig;

import java.util.Date;
import java.util.List;


public interface SysConfigDao extends BaseDao<SysConfig>{

    SysConfig getByKey(String key);

    List<SysConfig> page(int pageNum, int pageSize);
}

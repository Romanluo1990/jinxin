package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Account;

import java.util.Date;
import java.util.List;

public interface AccountDao extends BaseDao<Account>{

    Account getByOpenId(String openId);

    void register(String openId, String nickName, int type, String phone);

    void register(String nickName, String userName, String password, String salt, int type, String phone);

    Account getByUserName(String username);

    List<Account> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize);
}

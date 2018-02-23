package roman.extramoney.jinxin.dao;


import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.common.Identify;

public interface AccountDao extends BaseDao<Account>{

    Account getByOpenId(String openId);

    void register(String openId, String nickName);
}

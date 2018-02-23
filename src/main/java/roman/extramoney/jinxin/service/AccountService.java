package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.impl.AccountDaoImpl;
import roman.extramoney.jinxin.model.Account;

@Service
public class AccountService extends BaseService<AccountDao,Account>{

    public Account getByOpenId(String openId) {
        return dao.getByOpenId(openId);
    }

    public void register(String openId, String nickName) {
        dao.register(openId,nickName);
    }
}

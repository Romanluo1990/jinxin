package roman.extramoney.jinxin.dao.impl;

import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.mapper.AccountMapper;
import roman.extramoney.jinxin.model.Account;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<AccountMapper,Account> implements AccountDao{

    @Override
    public Account getByOpenId(String openId) {
        Account account = new Account();
        account.setOpenId(openId);
        return mapper.selectOne(account);
    }

    @Override
    public void register(String openId, String nickName) {
        Account account = new Account();
        account.setOpenId(openId);
        account.setNickName(nickName);
        mapper.insertSelective(account);
    }
}

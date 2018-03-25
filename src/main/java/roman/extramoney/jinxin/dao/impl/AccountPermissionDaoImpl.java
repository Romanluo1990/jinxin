package roman.extramoney.jinxin.dao.impl;

import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.AccountPermissionDao;
import roman.extramoney.jinxin.dao.mapper.AccountMapper;
import roman.extramoney.jinxin.dao.mapper.AccountPermissionMapper;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.AccountPermission;

import java.util.List;

@Repository
public class AccountPermissionDaoImpl extends BaseDaoImpl<AccountPermissionMapper,AccountPermission> implements AccountPermissionDao {
    @Override
    public List<String> listPermissionByAccountId(long accountId) {
        return mapper.listPermissionByAccountId(accountId);
    }

    @Override
    public void deleteByAccountId(long accountId) {
        AccountPermission accountPermission = new AccountPermission();
        accountPermission.setAccountId(accountId);
        mapper.delete(accountPermission);
    }
}

package roman.extramoney.jinxin.service;

import org.springframework.stereotype.Service;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.AccountPermissionDao;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.AccountPermission;

import java.util.List;

@Service
public class AccountPermissionService extends BaseService<AccountPermissionDao,AccountPermission>{


    public List<String> listPermissionByAccountId(long id) {
        return dao.listPermissionByAccountId(id);
    }

    public void deleteByAccountId(long accountId) {
        dao.deleteByAccountId(accountId);
    }
}

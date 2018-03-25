package roman.extramoney.jinxin.dao;

import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.AccountPermission;

import java.util.List;

public interface AccountPermissionDao extends BaseDao<AccountPermission>{

    List<String> listPermissionByAccountId(long accountId);

    void deleteByAccountId(long accountId);
}

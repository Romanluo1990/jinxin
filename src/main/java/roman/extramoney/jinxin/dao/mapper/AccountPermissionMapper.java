package roman.extramoney.jinxin.dao.mapper;

import org.apache.ibatis.annotations.Param;
import roman.extramoney.jinxin.model.AccountPermission;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AccountPermissionMapper extends Mapper<AccountPermission> {
    List<String> listPermissionByAccountId(@Param("accountId") long accountId);
}
package roman.extramoney.jinxin.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.service.AccountPermissionService;
import roman.extramoney.jinxin.service.AccountService;
import roman.extramoney.jinxin.service.WXService;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

public class UserRealm extends AuthorizingRealm {
    @Resource
    private AccountService accountService;

    @Resource
    private AccountPermissionService accountPermissionService;

    /**
     * 提供用户信息返回权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountDto accountDto = (AccountDto) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(new HashSet<>(accountDto.getPermissions()));
        return authorizationInfo;
    }

    /**
     * 提供账户信息返回认证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        Account account = accountService.getByUserName(username);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccount(account);
        List<String> permissions = accountPermissionService.listPermissionByAccountId(accountDto.getAccount().getId());
        accountDto.setPermissions(permissions);
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accountDto,
                account.getPassword(), ByteSource.Util.bytes(account.getSalt()), getName());
        return authenticationInfo;
    }
}

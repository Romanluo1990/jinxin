package roman.extramoney.jinxin.config.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.service.AccountPermissionService;
import roman.extramoney.jinxin.service.WXService;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;

public class WxRealm extends AuthorizingRealm {

    @Resource
    private WXService wxService;

    @Resource
    private AccountPermissionService accountPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        AccountDto accountDto = (AccountDto) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setStringPermissions(new HashSet<>(accountDto.getPermissions()));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String code = (String)token.getPrincipal();
        Account account = wxService.login(code);
        AccountDto accountDto = new AccountDto();
        accountDto.setAccount(account);
        List<String> permissions = accountPermissionService.listPermissionByAccountId(accountDto.getAccount().getId());
        accountDto.setPermissions(permissions);
        AuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(accountDto,token.getCredentials(),getName());
        return authenticationInfo;

    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof WxToken;
    }
}

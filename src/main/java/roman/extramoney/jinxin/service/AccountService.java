package roman.extramoney.jinxin.service;

import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roman.extramoney.jinxin.config.shiro.AccountDto;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.impl.AccountDaoImpl;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.AccountPermission;
import roman.extramoney.jinxin.model.Bridge;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService extends BaseService<AccountDao,Account>{

    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    private final String algorithmName = "md5";

    private final int hashIterations = 2;

    @Resource
    private AccountPermissionService accountPermissionService;


    public Account getByOpenId(String openId) {
        return dao.getByOpenId(openId);
    }

    public void register(String openId,  String nickName,int type,String phone) {
        dao.register(openId,nickName,type,phone);
    }

    public void register(String nickName, String userName,  String password, String phone) {
        String salt = randomNumberGenerator.nextBytes().toHex();
        dao.register(nickName,userName,encryptPassword(password,salt),salt,4,phone);
    }

    private String encryptPassword(String password, String salt) {
        return new SimpleHash(algorithmName, password,
                ByteSource.Util.bytes(salt), hashIterations).toHex();
    }

    public Account getByUserName(String username) {
        return dao.getByUserName(username);
    }

    @Transactional
    public void audit(long id, int status, String permissions, String statusMessage) {
        Account account = new Account();
        account.setId(id);
        account.setStatus(status);
        account.setStatusMessage(statusMessage);
        dao.updateByIdSelective(account);
        if(status == 1 && StringUtils.isNotEmpty(permissions))
            Arrays.stream(permissions.split(","))
                    .map(permission -> createPermission(id,permission))
                    .forEach(accountPermissionService::saveOrUpdate);
    }

    private AccountPermission createPermission(long accountId, String permission) {
        AccountPermission accountPermission = new AccountPermission();
        accountPermission.setAccountId(accountId);
        accountPermission.setPermission(permission);
        return accountPermission;
    }

    @Transactional
    public void permissionUpdate(long accountId, String permissions) {
        Account account = getById(accountId);
        if(account!=null && account.getStatus() == 1){
            accountPermissionService.deleteByAccountId(accountId);
            Arrays.stream(permissions.split(","))
                    .map(permission -> createPermission(accountId,permission))
                    .forEach(accountPermissionService::saveOrUpdate);
        }
    }

    public PageInfo<AccountDto> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        List<Account> accounts = dao.page(status,fromDate,toDate,pageNum,pageSize);
        return new PageInfo<>(accounts.stream().map(this::toAccountDto).collect(Collectors.toList()));
    }

    private AccountDto toAccountDto(Account account) {
        AccountDto accountDto = new AccountDto();
        accountDto.setAccount(account);
        accountDto.setPermissions(accountPermissionService.listPermissionByAccountId(account.getId()));
        return accountDto;
    }

    public void updatePwd(long id, String oldPwd, String newPwd) {
        Account account = dao.getById(id);
        String encryptOldPwd = encryptPassword(oldPwd,account.getSalt());
        if(!account.getPassword().equals(encryptOldPwd))
            throw new JInXinRunTimeException(412);
        account.setPassword(encryptPassword(newPwd,account.getSalt()));
        saveOrUpdate(account);
    }
}

package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.mapper.AccountMapper;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.BuildingProperty;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class AccountDaoImpl extends BaseDaoImpl<AccountMapper,Account> implements AccountDao{

    @Override
    public Account getByOpenId(String openId) {
        Account account = new Account();
        account.setOpenId(openId);
        return mapper.selectOne(account);
    }

    @Override
    public void register(String openId, String nickName, int type, String phone) {
        Account account = new Account();
        account.setOpenId(openId);
        account.setNickName(nickName);
        account.setType(type);
        account.setPhone(phone);
        mapper.insertSelective(account);
    }

    @Override
    public void register(String nickName, String userName, String password, String salt, int type, String phone) {
        Account account = new Account();
        account.setUserName(userName);
        account.setNickName(nickName);
        account.setPassword(password);
        account.setSalt(salt);
        account.setType(type);
        account.setPhone(phone);
        mapper.insertSelective(account);
    }

    @Override
    public Account getByUserName(String username) {
        Account account = new Account();
        account.setUserName(username);
        return mapper.selectOne(account);
    }

    @Override
    public List<Account> page(Integer status, Date fromDate, Date toDate, int pageNum, int pageSize) {
        Example example = new Example(Account.class);
        Example.Criteria criteria = example.createCriteria();
        if(status != null){
            criteria.andEqualTo("status",status);
        }
        if(fromDate != null){
            criteria.andGreaterThanOrEqualTo("createTime",fromDate);
        }
        if(toDate != null){
            criteria.andLessThanOrEqualTo("createTime",toDate);
        }
        PageHelper.startPage(pageNum,pageSize);
        PageHelper.orderBy("update_time desc");
        return mapper.selectByExample(example);
    }
}

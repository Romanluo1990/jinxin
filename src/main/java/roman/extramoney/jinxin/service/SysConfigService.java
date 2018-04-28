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
import roman.extramoney.jinxin.dao.SysConfigDao;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.AccountPermission;
import roman.extramoney.jinxin.model.SysConfig;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysConfigService extends BaseService<SysConfigDao,SysConfig>{


    public SysConfig getByKey(String key) {
        return dao.getByKey(key);
    }

    public PageInfo<SysConfig> page(int pageNum, int pageSize) {
        return new PageInfo<>(dao.page(pageNum,pageSize));
    }
}

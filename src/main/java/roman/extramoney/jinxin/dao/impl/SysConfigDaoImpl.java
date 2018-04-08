package roman.extramoney.jinxin.dao.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Repository;
import roman.extramoney.jinxin.dao.AccountDao;
import roman.extramoney.jinxin.dao.SysConfigDao;
import roman.extramoney.jinxin.dao.mapper.AccountMapper;
import roman.extramoney.jinxin.dao.mapper.SysConfigMapper;
import roman.extramoney.jinxin.model.Account;
import roman.extramoney.jinxin.model.SysConfig;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Repository
public class SysConfigDaoImpl extends BaseDaoImpl<SysConfigMapper,SysConfig> implements SysConfigDao {


    @Override
    public SysConfig getByKey(String key) {
        SysConfig sysConfig = new SysConfig();
        sysConfig.setKey(key);
        return mapper.selectOne(sysConfig);
    }

    @Override
    public List<SysConfig> page(int pageNum, int pageSize) {
        PageHelper.offsetPage(pageNum,pageSize);
        return mapper.selectAll();
    }
}

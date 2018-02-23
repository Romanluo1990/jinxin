package roman.extramoney.jinxin.dao;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.common.Identify;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;

public interface BaseDao<E extends Identify>{

    void save(E e);

    void deleteById(long id);

    void updateById(E e);

    void updateByIdSelective(E e);

    E getById(long id);

    void saveOrUpdate(E e);

}

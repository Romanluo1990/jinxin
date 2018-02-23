package roman.extramoney.jinxin.dao.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import roman.extramoney.jinxin.dao.BaseDao;
import roman.extramoney.jinxin.exception.JInXinRunTimeException;
import roman.extramoney.jinxin.model.common.Identify;
import tk.mybatis.mapper.common.Mapper;

import java.lang.reflect.ParameterizedType;

public class BaseDaoImpl<M extends Mapper<E>, E extends Identify> implements BaseDao<E>{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private Class classCache;

    @Autowired
    protected M mapper;

    @Override
    public void save(E e) {
        e.setId(null);
        mapper.insertSelective(e);
    }

    @Override
    public void deleteById(long id) {
        mapper.deleteByPrimaryKey(id);
    }

    @Override
    public void updateById(E e) {
        if (e.getId() == null)
            throw new JInXinRunTimeException("updateById method id cannot be null");
        mapper.updateByPrimaryKey(e);
    }

    @Override
    public void updateByIdSelective(E e) {
        if (e.getId() == null)
            throw new JInXinRunTimeException("updateById method id cannot be null");
        mapper.updateByPrimaryKeySelective(e);
    }

    @Override
    public E getById(long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveOrUpdate(E e) {
        if (e.getId() == null)
            save(e);
        else
            updateByIdSelective(e);
    }

    protected E createEntity() {
        Class clazz = getEntityClass();
        E e;
        try {
            e = (E) (clazz.getConstructor().newInstance());
        } catch (Exception ex) {
            logger.error("没有无参构造", ex);
            throw new JInXinRunTimeException(ex);
        }
        return e;
    }

    protected Class getEntityClass() {
        if (classCache == null) {
            ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
            classCache = (Class) parameterizedType.getActualTypeArguments()[1];
        }
        return classCache;
    }

}

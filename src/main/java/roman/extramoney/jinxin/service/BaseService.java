package roman.extramoney.jinxin.service;

import org.springframework.beans.factory.annotation.Autowired;
import roman.extramoney.jinxin.dao.BaseDao;
import roman.extramoney.jinxin.dao.impl.BaseDaoImpl;
import roman.extramoney.jinxin.model.common.Identify;

public class BaseService<D extends BaseDao<E>,E extends Identify> {

    @Autowired
    protected D dao;

    public void save(E e) {
        dao.save(e);
    }

    public void deleteById(long id) {
        dao.deleteById(id);
    }

    public void updateById(E e) {
        dao.updateById(e);
    }

    public void updateByIdSelective(E e) {
        dao.updateByIdSelective(e);
    }

    public E getById(long id) {
        return dao.getById(id);
    }

    public void saveOrUpdate(E e) {
        dao.saveOrUpdate(e);
    }
}

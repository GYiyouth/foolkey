package foolkey.pojo.root.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Resource
@Transactional
public class BaseDAO<T> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public T get(Class<T> entityClazz, Serializable id) {
        return hibernateTemplate.get(entityClazz,id);
    }

    public void save(T entity) {
        hibernateTemplate.save(entity);
    }

    public void update(T entity) {
        hibernateTemplate.update(entity);
    }

    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }

}

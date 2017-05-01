package foolkey.pojo.root.DAO.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("updateBaseDAO")
@Transactional
public class UpdateBaseDAO<T> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 更新实体
     *
     * @param entity
     */
    public T update(T entity) {
        hibernateTemplate.saveOrUpdate(entity);
        return entity;
    }
}

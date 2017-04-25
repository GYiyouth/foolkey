package foolkey.pojo.root.DAO.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("updateBaaseDAO")
public class UpdateBaseDAO<T> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 更新实体
     *
     * @param entity
     */
    public void update(T entity) {
        hibernateTemplate.saveOrUpdate(entity);
    }
}

package foolkey.pojo.root.DAO.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("saveBaseDAO")
public class SaveBaseDAO<T> {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    /**
     * 保存实体
     *
     * @param entity
     */
    public void save(T entity) {
        hibernateTemplate.save(entity);
    }
}

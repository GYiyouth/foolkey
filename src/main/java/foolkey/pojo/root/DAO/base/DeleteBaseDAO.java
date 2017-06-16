package foolkey.pojo.root.DAO.base;


import foolkey.tool.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("deleteBaseDAO")
public class DeleteBaseDAO<T> {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    /**
     * 删除实体
     *
     * @param entity
     */
    public void delete(T entity) {
        hibernateTemplate.delete(entity);
    }


    /**
     *
     * @description  deleteById 这个方法容易出错
     * @author       geyao
     * @date         2017/6/16
     *
     * @param entityClazz
     * @param id
     */
    @Deprecated
    public void deleteById(Class<T> entityClazz, Serializable id) {
        GetBaseDAO<T> getBaseDAO = BeanFactory.getBean("getBaseDAO", GetBaseDAO.class);
        delete(getBaseDAO.get(entityClazz, id));
    }

}

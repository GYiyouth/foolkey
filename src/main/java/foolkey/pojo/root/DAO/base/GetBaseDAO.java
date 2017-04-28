package foolkey.pojo.root.DAO.base;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2017/4/25.
 */
@Repository("getBaseDAO")
public class GetBaseDAO<T> {

    @Autowired
    protected HibernateTemplate hibernateTemplate;

    /**
     * 根据ID加载实体
     *
     * @param entityClazz
     * @param id
     * @return
     */
    public T get(Class<T> entityClazz, Serializable id) {
        return hibernateTemplate.get(entityClazz, id);
    }

    /**
     * 查询全部
     *
     * @param entityClazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public List<T> findAll(Class<T> entityClazz) {
        return (List<T>) hibernateTemplate.find("select en from "
                + entityClazz.getSimpleName() + " en");
    }

    /**
     * 查询表中记录数目
     *
     * @param entityClazz
     * @return
     */
    public long findCount(Class<T> entityClazz) {
        List<Long> list = (List<Long>) hibernateTemplate.find("select count(*) from " + entityClazz.getSimpleName() + " en");
        return list.get(0);
    }

    /**
     * 使用HQL语句进行分页查询
     *
     * @param hql      需要查询的HQL语句
     * @param pageNo   查询第pageNo页的记录
     * @param pageSize 每页要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    public List<T> findByPage(final String hql, final int pageNo, final int pageSize) {
        List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                //执行Hibernate分页查询
                List<T> result = session.createQuery(hql)
                        .setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
                return result;
            }
        });
        return list;
    }

    /**
     * 使用HQL语句进行分页查询
     *
     * @param hql      需要查询的HQL语句
     * @param pageNo   查询第pageNo页的记录
     * @param pageSize 每页要显示的记录数
     * @return 当前页的所有记录
     */
    @SuppressWarnings("unchecked")
    public List<T> findByPage(final String hql, final int pageNo, final int pageSize, final Object... params) {
        List<T> list = hibernateTemplate.execute(new HibernateCallback<List<T>>() {
            @Override
            public List<T> doInHibernate(Session session) throws HibernateException {
                //执行Hibernate分页查询
                Query query = session.createQuery(hql);
                //为包含占位符的HQL语句设置参数
                for (int i = 0, len = params.length; i < len; i++) {
                    query.setParameter(i , params[i]);
                }
                List<T> result = query.setFirstResult((pageNo - 1) * pageSize)
                        .setMaxResults(pageSize)
                        .list();
                return result;
            }
        });
        return list;
    }


}

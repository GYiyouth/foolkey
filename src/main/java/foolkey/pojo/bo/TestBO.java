package foolkey.pojo.bo;

import foolkey.pojo.vo.dto.StudentDTO;
import foolkey.tool.BeanFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/4/24.
 */
@Service
@Transactional(readOnly = false)
public class TestBO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(StudentDTO studentDTO){
        hibernateTemplate.save(studentDTO);
    }

    public void save2(StudentDTO studentDTO){
        Session session = BeanFactory.getSessionFactory().getCurrentSession();
        session.save(studentDTO);
    }

    public void get(Long id){
        hibernateTemplate.get(StudentDTO.class, id);
    }
}

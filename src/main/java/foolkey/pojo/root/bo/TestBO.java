package foolkey.pojo.root.bo;

import foolkey.pojo.send_to_client.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geyao on 2017/4/25.
 */
@Service
@Transactional
public class TestBO {
    @Autowired
    private HibernateTemplate hibernateTemplate;

    public void save(StudentDTO studentDTO){
        hibernateTemplate.save(studentDTO);
    }

    @Scheduled(fixedRate = 3000)
    public void foo(){
        System.out.println(1);
    }
}


package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/5/6.
 */
@Entity
@Component
@Table(name = "t_evaluation_teacher")
public class EvaluationTeacherDTO extends EvaluationAbstract {
}

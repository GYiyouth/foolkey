package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_order_buy_course")
public class OrderBuyCourseDTO extends OrderAbstract{

    private Long courseId;

    private Long teacherId;

    private Double number;

    private Double cutOffPercent;

    private Long couponId;


}

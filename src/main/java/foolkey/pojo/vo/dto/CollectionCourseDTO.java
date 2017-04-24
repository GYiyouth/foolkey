package foolkey.pojo.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_collection_course")
public class CollectionCourseDTO extends CollectionAbstract{

    @Column(name = "course_id")
    private Long courseId;

    @Override
    public String toString() {
        return "CollectionCourseDTO{" +
                "courseId=" + courseId +
                '}';
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}

package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 收藏的课程
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_collection_course")
public class CollectionCourseDTO extends CollectionAbstract{

    //收藏的课程id
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

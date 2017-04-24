package foolkey.pojo.root.vo.dto;

import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 评价课程的图片，最多四张
 * Created by geyao on 2017/4/24.
 */
@Entity
@Component
@Table(name = "t_evaluation_course")
public class EvaluationCourseDTO extends EvaluationAbstract {

    @Column(name = "content")
    private String content;

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "pic1Path")
    private String pic1Path;

    @Column(name = "pic2Path")
    private String pic2Path;

    @Column(name = "pic3Path")
    private String pic3Path;

    @Column(name = "pic4Path")
    private String pic4Path;

    public EvaluationCourseDTO() {
        super();
    }

    @Override
    public String toString() {
        return "EvaluationCourseDTO{" +
                "content='" + content + '\'' +
                ", courseId=" + courseId +
                ", pic1Path='" + pic1Path + '\'' +
                ", pic2Path='" + pic2Path + '\'' +
                ", pic3Path='" + pic3Path + '\'' +
                ", pic4Path='" + pic4Path + '\'' +
                "} " + super.toString();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getPic1Path() {
        return pic1Path;
    }

    public void setPic1Path(String pic1Path) {
        this.pic1Path = pic1Path;
    }

    public String getPic2Path() {
        return pic2Path;
    }

    public void setPic2Path(String pic2Path) {
        this.pic2Path = pic2Path;
    }

    public String getPic3Path() {
        return pic3Path;
    }

    public void setPic3Path(String pic3Path) {
        this.pic3Path = pic3Path;
    }

    public String getPic4Path() {
        return pic4Path;
    }

    public void setPic4Path(String pic4Path) {
        this.pic4Path = pic4Path;
    }
}

package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import foolkey.pojo.root.vo.assistObject.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Table(name = "t_student")
@Component
public class StudentDTO  extends AbstractDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //用户名，一般是手机号
    private String userName;

    //密码
    private String passWord;

    //账上现金，人民币
    @Column(name = "cash")
    private Double cash;

    //账上虚拟币
    @Column(name = "virtual_currency")
    private Double virtualCurrency;

    //声望数
    private Integer prestige;


    //真名
    private String name;

    @Column(name = "sex")
    private SexTagEnum sexTagEnum;

    //用户所属组织
    private String organization;

    private Date birthday;

    //用户的技术标签
    @Column(name = "current_tag")
    private TechnicTagEnum technicTagEnum;
    //用户最高学历
    @Column(name = "top_school_graduation")
    private SchoolEnum schoolEnum;

    private String blogUrl;

    private String githubUrl;

    private String otherUrl;

    //头像链接
    private String photoUrl;

    //昵称
    private String nickedName;

    //角色 student, teacher, alreadyApplied ，后面2者均代表这名用户已经有了自己的teacherDTO
    @Column(name = "role")
    @Enumerated(EnumType.ORDINAL)
    private RoleEnum roleEnum;

    //用户状态 unverified, normal, temporaryBan, permanentBan ,目前默认都是normal
    @Column(name = "userState")
    @Enumerated(EnumType.ORDINAL)
    private UserStateEnum userStateEnum;

    //用户的口号，相当于个性签名
    private String slogan;

    private String email;

    private String phoneNumber;

    //用户对自己的详细描述
    private String description;

    @Column(name = "averageScore")
    private Float studentAverageScore;

    //学习时长
    @Column(name = "learning_time")
    private Float learningTime;
    //上课次数，包括悬赏和老师课程
    @Column(name = "learning_number")
    private Integer learningNumber;

    public StudentDTO() {
        super();
    }


    @Override
    public String toString() {
        return "StudentDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", cash=" + cash +
                ", virtualCurrency=" + virtualCurrency +
                ", prestige=" + prestige +
                ", name='" + name + '\'' +
                ", sexTagEnum=" + sexTagEnum +
                ", organization='" + organization + '\'' +
                ", birthday=" + birthday +
                ", technicTagEnum=" + technicTagEnum +
                ", schoolEnum=" + schoolEnum +
                ", blogUrl='" + blogUrl + '\'' +
                ", githubUrl='" + githubUrl + '\'' +
                ", otherUrl='" + otherUrl + '\'' +
                ", photoUrl='" + photoUrl + '\'' +
                ", nickedName='" + nickedName + '\'' +
                ", roleEnum=" + roleEnum +
                ", userStateEnum=" + userStateEnum +
                ", slogan='" + slogan + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", description='" + description + '\'' +
                ", studentAverageScore=" + studentAverageScore +
                ", learningTime=" + learningTime +
                ", learningNumber=" + learningNumber +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public Double getCash() {
        return cash;
    }

    public void setCash(Double cash) {
        this.cash = cash;
    }

    public Double getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(Double virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexTagEnum getSexTagEnum() {
        return sexTagEnum;
    }

    public void setSexTagEnum(SexTagEnum sexTagEnum) {
        this.sexTagEnum = sexTagEnum;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public TechnicTagEnum getTechnicTagEnum() {
        return technicTagEnum;
    }

    public void setTechnicTagEnum(TechnicTagEnum technicTagEnum) {
        this.technicTagEnum = technicTagEnum;
    }

    public SchoolEnum getSchoolEnum() {
        return schoolEnum;
    }

    public void setSchoolEnum(SchoolEnum schoolEnum) {
        this.schoolEnum = schoolEnum;
    }

    public String getBlogUrl() {
        return blogUrl;
    }

    public void setBlogUrl(String blogUrl) {
        this.blogUrl = blogUrl;
    }

    public String getGithubUrl() {
        return githubUrl;
    }

    public void setGithubUrl(String githubUrl) {
        this.githubUrl = githubUrl;
    }

    public String getOtherUrl() {
        return otherUrl;
    }

    public void setOtherUrl(String otherUrl) {
        this.otherUrl = otherUrl;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getNickedName() {
        return nickedName;
    }

    public void setNickedName(String nickedName) {
        this.nickedName = nickedName;
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public void setRoleEnum(RoleEnum roleEnum) {
        this.roleEnum = roleEnum;
    }

    public UserStateEnum getUserStateEnum() {
        return userStateEnum;
    }

    public void setUserStateEnum(UserStateEnum userStateEnum) {
        this.userStateEnum = userStateEnum;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getStudentAverageScore() {
        return studentAverageScore;
    }

    public void setStudentAverageScore(Float studentAverageScore) {
        this.studentAverageScore = studentAverageScore;
    }

    public Float getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Float learningTime) {
        this.learningTime = learningTime;
    }

    public Integer getLearningNumber() {
        return learningNumber;
    }

    public void setLearningNumber(Integer learningNumber) {
        this.learningNumber = learningNumber;
    }

    public void myClone(StudentDTO target, StudentDTO source) throws Exception{
        Field[] fields = source.getClass().getDeclaredFields();
        for (Field field : fields) {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), source.getClass());
            Method setMethod = descriptor.getWriteMethod();
            Method getMethod = descriptor.getReadMethod();
            setMethod.invoke(target, getMethod.invoke(source));
        }
    }
}

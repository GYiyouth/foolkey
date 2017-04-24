package foolkey.pojo.vo.dto;

import foolkey.pojo.vo.assistObject.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by geyao on 2017/4/24.
 */
@Entity
@Table(name = "t_student")
@Component
public class StudentDTO {
    @Id
    @GeneratedValue
    private Long id;

    private String userName;

    private String passWord;

    private Integer cash;

    @Column(name = "virtual_currency")
    private Integer virtualCurrency;

    private Integer prestige;

    private Integer name;

    @Column(name = "sex")
    private SexTagEnum sexTagEnum;

    private String organization;

    private Date birthday;

    @Column(name = "current_tag")
    private IndustryTagEnum industryTagEnum;
    @Column(name = "top_school_graduation")
    private SchoolEnum schoolEnum;

    private String blogUrl;

    private String githubUrl;

    private String otherUrl;

    private String photoUrl;

    private String nickedName;

    @Column(name = "role")
    private RoleEnum roleEnum;

    @Column(name = "userState")
    private UserStateEnum userStateEnum;

    private String slogan;

    private String email;

    private String phoneNumber;

    private String description;

    @Column(name = "averageScore")
    private Integer studentAverageScore;

    @Column(name = "learning_time")
    private Integer learningTime;

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
                ", name=" + name +
                ", sexTagEnum=" + sexTagEnum +
                ", organization='" + organization + '\'' +
                ", birthday=" + birthday +
                ", industryTagEnum=" + industryTagEnum +
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

    public Integer getCash() {
        return cash;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }

    public Integer getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(Integer virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public Integer getPrestige() {
        return prestige;
    }

    public void setPrestige(Integer prestige) {
        this.prestige = prestige;
    }

    public Integer getName() {
        return name;
    }

    public void setName(Integer name) {
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

    public IndustryTagEnum getIndustryTagEnum() {
        return industryTagEnum;
    }

    public void setIndustryTagEnum(IndustryTagEnum industryTagEnum) {
        this.industryTagEnum = industryTagEnum;
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

    public Integer getStudentAverageScore() {
        return studentAverageScore;
    }

    public void setStudentAverageScore(Integer studentAverageScore) {
        this.studentAverageScore = studentAverageScore;
    }

    public Integer getLearningTime() {
        return learningTime;
    }

    public void setLearningTime(Integer learningTime) {
        this.learningTime = learningTime;
    }

    public Integer getLearningNumber() {
        return learningNumber;
    }

    public void setLearningNumber(Integer learningNumber) {
        this.learningNumber = learningNumber;
    }
}
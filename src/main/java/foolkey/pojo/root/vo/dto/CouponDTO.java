package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.assistObject.CouponTypeEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_coupon")
public class CouponDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "owner_id")
    private Long ownerId;

    @Column(name = "name")
    private String name;

    @Column(name = "level")
    private Double level;// 门槛

    @Column(name = "value")
    private Double value;

    @Column(name = "release_time")
    private Date releaseTime;

    @Column(name = "dead_time")
    private Date deadTime;

    @Column(name = "coupon_type")
    @Enumerated(EnumType.ORDINAL)
    private CouponTypeEnum couponTypeEnum;

    @Override
    public String toString() {
        return "CouponDTO{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", value=" + value +
                ", releaseTime=" + releaseTime +
                ", deadTime=" + deadTime +
                ", couponTypeEnum=" + couponTypeEnum +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLevel() {
        return level;
    }

    public void setLevel(Double level) {
        this.level = level;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Date getDeadTime() {
        return deadTime;
    }

    public void setDeadTime(Date deadTime) {
        this.deadTime = deadTime;
    }

    public CouponTypeEnum getCouponTypeEnum() {
        return couponTypeEnum;
    }

    public void setCouponTypeEnum(CouponTypeEnum couponTypeEnum) {
        this.couponTypeEnum = couponTypeEnum;
    }
}

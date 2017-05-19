package foolkey.pojo.root.vo.dto;

import foolkey.pojo.root.vo.AbstractDTO;
import foolkey.pojo.root.vo.assistObject.CouponTypeEnum;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * 优惠券
 * Created by admin on 2017/4/24.
 */
@Component
@Entity
@Table(name = "t_coupon")
public class CouponDTO  extends AbstractDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //拥有者
    @Column(name = "owner_id")
    private Long ownerId;

    //名字
    @Column(name = "name")
    private String name;

    // 门槛
    @Column(name = "level")
    private Double level;

    //价值
    @Column(name = "value")
    private Double value;

    //发放时间
    @Column(name = "release_time")
    private Date releaseTime;

    //过期时间，如果使用了，则整个券被删除
    @Column(name = "dead_time")
    private Date deadTime;

    //优惠券类型
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

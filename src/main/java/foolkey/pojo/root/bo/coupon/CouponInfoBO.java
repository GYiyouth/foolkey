package foolkey.pojo.root.bo.coupon;

import foolkey.pojo.root.DAO.coupon.DeleteCouponDAO;
import foolkey.pojo.root.DAO.coupon.GetCouponDAO;
import foolkey.pojo.root.vo.dto.CouponDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by geyao on 2017/5/1.
 */
@Service
@Transactional(readOnly = true)
public class CouponInfoBO {

    @Autowired
    private GetCouponDAO getCouponDAO;
    @Autowired
    private DeleteCouponDAO deleteCouponDAO;

    /**
     * 根据id获取优惠券信息
     * @param couponId
     * @return
     */
    public CouponDTO getCouponDTO(String couponId){
        if (couponId == null || couponId.equals(""))
            return null;
        return getCouponDAO.get(CouponDTO.class, Long.parseLong(couponId));
    }
    /**
     * 根据id获取优惠券信息
     * @param couponId
     * @return
     */
    public CouponDTO getCouponDTO(Long couponId){
        if (couponId == null || couponId.equals(""))
            return null;
        return getCouponDAO.get(CouponDTO.class, couponId);
    }

    /**
     * 获取某个人的所有可用优惠券
     * @param studentId
     * @return
     */
    public List<CouponDTO> getCouponDTOList(String studentId, int pageNum, int pageSize){
        return
                getCouponDAO.findByPage(
                "from CouponDTO c where c.ownerId = ? and deadTime >= ?"
                ,pageNum
                ,pageSize
                ,Long.parseLong( studentId )
                ,new Date()
        );
    }

    /**
     * 删除优惠券
     * @param couponDTO
     */
    public void delete(CouponDTO couponDTO){
        deleteCouponDAO.delete(couponDTO);
    }
}

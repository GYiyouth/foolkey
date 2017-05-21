package foolkey.pojo.root.vo.assistObject;

/**
 * 付款时，可能得到的结果
 *
 * Created by GR on 2017/5/21.
 */
public enum PayResultEnum {
   success,notEnoughBalance,notUseCoupon,other
}
//成功、余额不足、不能优惠券(没有这个优惠券、不到用优惠券级别...)、其他情况
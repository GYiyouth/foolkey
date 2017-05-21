package foolkey.pojo.root.CAO.Reward;

import com.alibaba.fastjson.JSON;
import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.RewardDTO;
import foolkey.pojo.send_to_client.reward.RewardWithStudentSTCDTO;
import foolkey.tool.StaticVariable;
import foolkey.tool.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ustcg on 2017/5/6.
 */
@Component("rewardCAO")
public class RewardCAO extends AbstractCAO {


    /**
     * c初始化，缓存中创建每个类别悬赏的空间
     */
    public void initRewardCache(){
        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
            List<String> list = new ArrayList<>();
            cache.set(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        }
    }

    /**
     * 根据技术关键字，拼接成搜索该类别悬赏的key
     *
     * @param technicTagEnum
     * @return
     */
    public String getRewardSearchKeyOfTechnicTagEnum(TechnicTagEnum technicTagEnum) {
        return technicTagEnum.toString() + Cache.separator + rewardPopularToken;
    }

    /**
     * 由悬赏id，拼接成一个存储悬赏对象的key
     *
     * @param rewardId
     * @return
     */
    public String getRewardKey(Long rewardId) {
        return rewardPopularToken + Cache.separator + rewardId;
    }

    /**
     * 创建一个类别的悬赏缓存，并存入一个数据
     *
     * @param technicTagEnum
     * @param rewardWithStudentSTCDTO
     */
    public void createRewardCache(TechnicTagEnum technicTagEnum, RewardWithStudentSTCDTO rewardWithStudentSTCDTO) {
        //创建list，放入缓存
        List<String> list = new ArrayList<>();
        list.add(0, getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()));
        //存list进缓存
        cache.set(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        //存悬赏-学生DTO入缓存
        cache.set(getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()), JSON.toJSONString(rewardWithStudentSTCDTO));
    }

    /**
     * 插入一个悬赏，此时缓存队列没有满
     *
     * @param list
     * @param technicTagEnum
     * @param rewardWithStudentSTCDTO
     * @param directionEnum
     */
    public void addRewardWithStudentToNotFullCache(List<String> list, TechnicTagEnum technicTagEnum, RewardWithStudentSTCDTO rewardWithStudentSTCDTO, DirectionEnum directionEnum) {

        if (directionEnum == DirectionEnum.head) {
            //如果是头插
            //插入id
            list.add(0, getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()));
        } else {
            //尾插
            //插入id
            list.add(getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()));
        }
        //存list入缓存
        cache.set(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        //悬赏-学生对象存储入缓存
        cache.set(getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()), JSON.toJSONString(rewardWithStudentSTCDTO));
    }

    /**
     * 插入一个悬赏，此时缓存队列满了
     *
     * @param list
     * @param technicTagEnum
     * @param rewardWithStudentSTCDTO
     * @param directionEnum
     */
    public void addRewardWithStudentToFullCache(List<String> list, TechnicTagEnum technicTagEnum, RewardWithStudentSTCDTO rewardWithStudentSTCDTO, DirectionEnum directionEnum) {
        if (directionEnum == DirectionEnum.head) {
            //如果是头插
            //插入id
            list.add(0, getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()));
            //删除超出缓存大小的节点
            for (int i = StaticVariable.CACHE_SIZE; i < list.size(); i++) {
                list.remove(i);
            }
            //存list入缓存
            cache.set(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
            //悬赏-学生对象存储入缓存
            cache.set(getRewardKey(rewardWithStudentSTCDTO.getRewardDTO().getId()), JSON.toJSONString(rewardWithStudentSTCDTO));
        } else {
            //尾插
            //不做操作
        }

    }

    /**
     * 添加一门悬赏-学生DTO入缓存
     *
     * @param technicTagEnum
     * @param rewardWithStudentSTCDTO
     * @param directionEnum
     */
    public void addRewardWithStudentToCache(TechnicTagEnum technicTagEnum, RewardWithStudentSTCDTO rewardWithStudentSTCDTO, DirectionEnum directionEnum) {

        //根据悬赏类别搜索key，获取缓存中的对应值
        String result = cache.getString(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum));

        //判断有没有这个类别的悬赏
        if (!isContainRewardWithStudent(technicTagEnum)) {
            //缓存中没有这个类别的悬赏
            createRewardCache(technicTagEnum, rewardWithStudentSTCDTO);
        } else {
            if(result.equals("")){
                result += "[]";
            }
            //缓存有了这个类别的悬赏
            List<String> list = JSON.parseArray(result, String.class);
            //判断缓存有没有满
            if (list.size() < StaticVariable.CACHE_SIZE) {
                //没有满
                addRewardWithStudentToNotFullCache(list, technicTagEnum, rewardWithStudentSTCDTO, directionEnum);
            } else {
                //满了
                addRewardWithStudentToFullCache(list, technicTagEnum, rewardWithStudentSTCDTO, directionEnum);
            }
        }
    }

    /**
     * 添加多门悬赏到缓存
     * 暂时不能头插，因为循环之后正好逆序。另外，暂不需要头插。
     *
     * @param technicTagEnum
     * @param rewardWithStudentSTCDTOS
     * @param directionEnum
     */
    public void addRewardWithStudentSToCache(TechnicTagEnum technicTagEnum, List<RewardWithStudentSTCDTO> rewardWithStudentSTCDTOS, DirectionEnum directionEnum) {
        for (RewardWithStudentSTCDTO rewardWithStudentSTCDTO : rewardWithStudentSTCDTOS) {
            addRewardWithStudentToCache(technicTagEnum, rewardWithStudentSTCDTO, DirectionEnum.tail);
        }
    }

    /**
     * 判断缓存中是否有该类别悬赏的DTO
     *
     * @param technicTagEnum
     * @return
     */
    public boolean isContainRewardWithStudent(TechnicTagEnum technicTagEnum) {
        //根据悬赏类别搜索key，获取缓存中的对应值
        String result = cache.getString(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum));
        if (result != null) {
            return true;
        }
        return false;
    }

    /**
     * 根据技术关键字分类判断缓存是否包含 足够多 这个门类的悬赏
     *
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public boolean isContainRewardWithStudentS(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //如果请求的大于定义的缓存大小
        if ((pageNo * pageSize) > StaticVariable.CACHE_SIZE) {
            return false;
        }
        //缓存有这个类别悬赏
        if (isContainRewardWithStudent(technicTagEnum)) {
            //根据悬赏类别搜索key，获取缓存中的对应值
            String result = cache.getString(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum));
            //去缓存中热门悬赏列表
            List<String> list = JSON.parseArray(result, String.class);
            //如果缓存中数目大于请求的数目
            if (list.size() >= ((pageNo - 1) * pageSize)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 缓存中分页取热门悬赏
     *
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<RewardWithStudentSTCDTO> getRewardWithStudentPopularFromCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //返回参数
        List<RewardWithStudentSTCDTO> rewardWithStudentSTCDTOS = new ArrayList<>();
        //缓存中有足够的结果
        if (isContainRewardWithStudentS(technicTagEnum, pageNo, pageSize)) {
            //根据悬赏类别搜索key，获取缓存中的对应值
            String result = cache.getString(getRewardSearchKeyOfTechnicTagEnum(technicTagEnum));
            //去缓存中热门悬赏列表
            List<String> list = JSON.parseArray(result, String.class);
            for (int i = (pageNo - 1) * pageSize; i < pageSize; i++) {
                //首先根据悬赏列表的值，找到缓存中对应的value
                String rewardWithStudentSTCDTOStr = cache.getString(list.get(i));
                RewardWithStudentSTCDTO rewardWithStudentSTCDTO = JSON.parseObject(rewardWithStudentSTCDTOStr, RewardWithStudentSTCDTO.class);
                rewardWithStudentSTCDTOS.add(rewardWithStudentSTCDTO);
            }
        }
        return rewardWithStudentSTCDTOS;
    }

    /**
     * 根据悬赏id，获取悬赏的内容
     *
     * @param id
     * @return
     */
    public RewardDTO getRewardDTOByRewardId(Long id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        } else {
            RewardWithStudentSTCDTO rewardWithStudentSTCDTO = getRewardWithStudentByRewardId(id);
            if (rewardWithStudentSTCDTO != null) {
                return rewardWithStudentSTCDTO.getRewardDTO();
            }
        }
        return null;
    }

    /**
     * 根据悬赏id，获取悬赏-学生DTO
     *
     * @param id
     * @return
     */
    public RewardWithStudentSTCDTO getRewardWithStudentByRewardId(Long id) {

        //要查找的悬赏id包装成的key
        String aimKey = getRewardKey(id);
        String result = cache.getString(aimKey);
        if (result != null) {
            return JSON.parseObject(result, RewardWithStudentSTCDTO.class);
        } else {
            return null;
        }
    }

    /**
     * 更新缓存中的悬赏-学生DTO
     * @param newTechnicTagEnum          修改后的类别
     * @param newRewardWithStudentSTCDTO
     */
    public void updateRewardWithStudent(TechnicTagEnum newTechnicTagEnum, RewardWithStudentSTCDTO newRewardWithStudentSTCDTO) {
        String aimRewardKey = getRewardKey(newRewardWithStudentSTCDTO.getRewardDTO().getId());
        //获取到旧的悬赏-学生DTO，目的是获取到修改前的类别，进而判断有没有修改类别
        String oldRewardWithStudentSTCDTOStr = cache.getString(aimRewardKey);
        RewardWithStudentSTCDTO oldRewardWithStudentSTCDTO = JSON.parseObject(oldRewardWithStudentSTCDTOStr, RewardWithStudentSTCDTO.class);
        TechnicTagEnum oldTechnicTagEnum = oldRewardWithStudentSTCDTO.getRewardDTO().getTechnicTagEnum();
        //修改缓存中悬赏-学生DTO
        cache.set(aimRewardKey, JSON.toJSONString(newRewardWithStudentSTCDTO));
        //判断有没有修改类别，修改了需要修改热门队列
        //没有修改，则不需要操作
        if (oldTechnicTagEnum == newTechnicTagEnum) {

        } else {
            //1. 首先获取到旧类别下的悬赏队列
            String listKey = getRewardSearchKeyOfTechnicTagEnum(oldTechnicTagEnum);
            String resultStr = cache.getString(listKey);
            List<String> list = JSON.parseArray(resultStr, String.class);
            //删除对应节点
            list.remove(list.indexOf(getRewardKey(newRewardWithStudentSTCDTO.getRewardDTO().getId())));
            //2. 添加到新类别的悬赏队列中
            addRewardWithStudentToCache(newRewardWithStudentSTCDTO.getRewardDTO().getTechnicTagEnum(), newRewardWithStudentSTCDTO, DirectionEnum.head);
        }
    }

    /**
     * 删除悬赏-学生DTO
     * @param aimRewardWithStudentSTCDTO
     */
    public void deleteRewardWithStudent(RewardWithStudentSTCDTO aimRewardWithStudentSTCDTO) {
    }


}
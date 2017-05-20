package foolkey.pojo.root.CAO.Course;

import com.alibaba.fastjson.JSON;
import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.pojo.send_to_client.course.CourseWithTeacherSTCDTO;
import foolkey.tool.StaticVariable;
import foolkey.tool.cache.Cache;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2017/4/27.
 */
@Component("courseCAO")
public class CourseCAO extends AbstractCAO {


    /**
     * c初始化，缓存中创建每个类别课程的空间
     */
    public void initCourseCache(){
        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
            List<String> list = new ArrayList<>();
            cache.set(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        }
    }

    /**
     * 根据技术关键字，拼接成搜索该类别课程的key
     *
     * @param technicTagEnum
     * @return
     */
    public String getCourseSearchKeyOfTechnicTagEnum(TechnicTagEnum technicTagEnum) {
        return technicTagEnum.toString() + Cache.separator + coursePopularToken;
    }

    /**
     * 由课程id，拼接成一个存储课程对象的key
     *
     * @param courseId
     * @return
     */
    public String getCourseKey(Long courseId) {
        return coursePopularToken + Cache.separator + courseId;
    }

    /**
     * 创建一个类别的课程缓存，并存入一个数据
     *
     * @param technicTagEnum
     * @param courseWithTeacherSTCDTO
     */
    public void createRewardCache(TechnicTagEnum technicTagEnum, CourseWithTeacherSTCDTO courseWithTeacherSTCDTO) {
        //创建list，放入缓存
        List<String> list = new ArrayList<>();
        list.add(0, getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()));
        //存list进缓存
        cache.set(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        //存课程-老师DTO入缓存
        cache.set(getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()), JSON.toJSONString(courseWithTeacherSTCDTO));
    }

    /**
     * 插入一个课程，此时缓存队列没有满
     *
     * @param list
     * @param technicTagEnum
     * @param courseWithTeacherSTCDTO
     * @param directionEnum
     */
    public void addCourseWithTeacherToNotFullCache(List<String> list, TechnicTagEnum technicTagEnum, CourseWithTeacherSTCDTO courseWithTeacherSTCDTO, DirectionEnum directionEnum) {

        if (directionEnum == DirectionEnum.head) {
            //如果是头插
            //插入id
            list.add(0, getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()));
        } else {
            //尾插
            //插入id
            list.add(getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()));
        }
        //存list入缓存
        cache.set(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
        //课程-老师对象存储入缓存
        cache.set(getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()), JSON.toJSONString(courseWithTeacherSTCDTO));
    }

    /**
     * 插入一个课程，此时缓存队列满了
     *
     * @param list
     * @param technicTagEnum
     * @param courseWithTeacherSTCDTO
     * @param directionEnum
     */
    public void addCourseWithTeacherToFullCache(List<String> list, TechnicTagEnum technicTagEnum, CourseWithTeacherSTCDTO courseWithTeacherSTCDTO, DirectionEnum directionEnum) {
        if (directionEnum == DirectionEnum.head) {
            //如果是头插
            //插入id
            list.add(0, getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()));
            //删除超出缓存大小的节点
            for (int i = StaticVariable.cacheSize; i < list.size(); i++) {
                list.remove(i);
            }
            //存list入缓存
            cache.set(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum), JSON.toJSONString(list));
            //课程-老师对象存储入缓存
            cache.set(getCourseKey(courseWithTeacherSTCDTO.getCourseDTO().getId()), JSON.toJSONString(courseWithTeacherSTCDTO));
        } else {
            //尾插
            //不做操作
        }

    }

    /**
     * 添加一门课程-老师DTO入缓存
     *
     * @param technicTagEnum
     * @param courseWithTeacherSTCDTO
     * @param directionEnum
     */
    public void addCourseWithTeacherToCache(TechnicTagEnum technicTagEnum, CourseWithTeacherSTCDTO courseWithTeacherSTCDTO, DirectionEnum directionEnum) {

        //根据课程类别搜索key，获取缓存中的对应值
        String result = cache.getString(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum));

        //判断有没有这个类别的课程
        if (!isContainCourseWithTeacher(technicTagEnum)) {
            //缓存中没有这个类别的课程
            createRewardCache(technicTagEnum, courseWithTeacherSTCDTO);
        } else {
            //缓存有了这个类别的课程
            if(result.equals("")){
                result+="[]";
                System.out.println(result);
            }
            List<String> list = JSON.parseObject(result, ArrayList.class);
            //判断缓存有没有满
            if (list.size() < StaticVariable.cacheSize) {
                //没有满
                addCourseWithTeacherToNotFullCache(list, technicTagEnum, courseWithTeacherSTCDTO, directionEnum);
            } else {
                //满了
                addCourseWithTeacherToFullCache(list, technicTagEnum, courseWithTeacherSTCDTO, directionEnum);
            }
        }
    }

    /**
     * 添加多门课程到缓存
     * 暂时不能头插，因为循环之后正好逆序。另外，暂不需要头插。
     *
     * @param technicTagEnum
     * @param courseWithTeacherSTCDTOS
     * @param directionEnum
     */
    public void addCourseWithTeacherSToCache(TechnicTagEnum technicTagEnum, List<CourseWithTeacherSTCDTO> courseWithTeacherSTCDTOS, DirectionEnum directionEnum) {
        for (CourseWithTeacherSTCDTO courseWithTeacherSTCDTO : courseWithTeacherSTCDTOS) {
            addCourseWithTeacherToCache(technicTagEnum, courseWithTeacherSTCDTO, DirectionEnum.tail);
        }
    }

    /**
     * 判断缓存中是否有该类别课程的DTO
     *
     * @param technicTagEnum
     * @return
     */
    public boolean isContainCourseWithTeacher(TechnicTagEnum technicTagEnum) {
        //根据课程类别搜索key，获取缓存中的对应值
        String result = cache.getString(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum));
        if (result != null ) {
            return true;
        }
        return false;
    }

    /**
     * 根据技术关键字分类判断缓存是否包含 足够多 这个门类的课程
     *
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public boolean isContainCourseWithTeacherS(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //如果请求的大于定义的缓存大小
        if ((pageNo * pageSize) > StaticVariable.cacheSize) {
            return false;
        }
        //缓存有这个类别课程
        if (isContainCourseWithTeacher(technicTagEnum)) {
            //根据课程类别搜索key，获取缓存中的对应值
            String result = cache.getString(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum));
            //去缓存中热门课程列表
            List<String> list = JSON.parseArray(result, String.class);
            //如果缓存中数目大于请求的数目
            if (list.size() > ((pageNo - 1) * pageSize)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 缓存中分页取热门课程
     *
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<CourseWithTeacherSTCDTO> getCourseWithTeacherPopularFromCache(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //返回参数
        List<CourseWithTeacherSTCDTO> courseWithTeacherSTCDTOS = new ArrayList<>();
        //缓存中有足够的结果
        if (isContainCourseWithTeacherS(technicTagEnum, pageNo, pageSize)) {
            //根据课程类别搜索key，获取缓存中的对应值
            String result = cache.getString(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum));
            //去缓存中热门课程列表
            List<String> list = JSON.parseArray(result, String.class);
            for (int i = (pageNo - 1) * pageSize; i < pageNo * pageSize && i < list.size(); i++) {
                //首先根据课程列表的值，找到缓存中对应的value
                String courseWithTeacherSTCDTOStr = cache.getString(list.get(i));
                CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = JSON.parseObject(courseWithTeacherSTCDTOStr, CourseWithTeacherSTCDTO.class);
                courseWithTeacherSTCDTOS.add(courseWithTeacherSTCDTO);
            }
        }
        return courseWithTeacherSTCDTOS;
    }

    /**
     * 根据课程id，获取课程的内容
     *
     * @param courseId
     * @return
     */
    public CourseDTO getCourseDTOByCourseId(Long courseId) {
        if (courseId == null) {
            throw new NullPointerException("id is null");
        } else {
            CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = getCourseWithTeacherByCourseId(courseId);
            if (courseWithTeacherSTCDTO != null) {
                return courseWithTeacherSTCDTO.getCourseDTO();
            }
        }
        return null;
    }

    /**
     * 根据课程id，获取课程-老师DTO
     *
     * @param courseId
     * @return
     */
    public CourseWithTeacherSTCDTO getCourseWithTeacherByCourseId(Long courseId) {

        //要查找的课程id包装成的key
        String aimKey = getCourseKey(courseId);
        String result = cache.getString(aimKey);
        if (result != null) {
            return JSON.parseObject(result, CourseWithTeacherSTCDTO.class);
        } else {
            return null;
        }
    }

    /**
     * 更新缓存中的课程-老师DTO
     * @param newTechnicTagEnum          修改后的类别
     * @param newCourseWithTeacherSTCDTO
     */
    public void updateCourseWithTeacher(TechnicTagEnum newTechnicTagEnum, CourseWithTeacherSTCDTO newCourseWithTeacherSTCDTO) {
        String aimRewardKey = getCourseKey(newCourseWithTeacherSTCDTO.getCourseDTO().getId());
        //获取到旧的课程-老师DTO，目的是获取到修改前的类别，进而判断有没有修改类别
        String oldCourseWithTeacherSTCDTOStr = cache.getString(aimRewardKey);
        CourseWithTeacherSTCDTO oldCourseWithTeacherSTCDTO = JSON.parseObject(oldCourseWithTeacherSTCDTOStr, CourseWithTeacherSTCDTO.class);
        TechnicTagEnum oldTechnicTagEnum = oldCourseWithTeacherSTCDTO.getCourseDTO().getTechnicTagEnum();
        //修改缓存中课程-老师DTO
        cache.set(aimRewardKey, JSON.toJSONString(newCourseWithTeacherSTCDTO));
        //判断有没有修改类别，修改了需要修改热门队列
        //没有修改，则不需要操作
        if (oldTechnicTagEnum == newTechnicTagEnum) {

        } else {
            //1. 首先获取到旧类别下的课程队列
            String listKey = getCourseSearchKeyOfTechnicTagEnum(oldTechnicTagEnum);
            String resultStr = cache.getString(listKey);
            List<String> list = JSON.parseArray(resultStr, String.class);
            //删除对应节点
            list.remove(list.indexOf(getCourseKey(newCourseWithTeacherSTCDTO.getCourseDTO().getId())));
            //2. 添加到新类别的课程队列中
            addCourseWithTeacherToCache(newCourseWithTeacherSTCDTO.getCourseDTO().getTechnicTagEnum(), newCourseWithTeacherSTCDTO, DirectionEnum.head);
        }
    }

    /**
     * 删除课程-老师DTO
     * @param aimCourseWithTeacherSTCDTO
     */
    public void deleteCourseWithTeacher(CourseWithTeacherSTCDTO aimCourseWithTeacherSTCDTO) {
    }



    /**
     * OK
     * 获取所有的热门课程
     * @param technicTagEnum
     * @return
     */
    public List<CourseWithTeacherSTCDTO> getAllCourseWithTeacherPopularFromCache(TechnicTagEnum technicTagEnum){
        ArrayList<CourseWithTeacherSTCDTO> courseWithTeacherSTCDTOS = new ArrayList<>();

            //根据课程类别搜索key，获取缓存中的对应值
            String result = cache.getString(getCourseSearchKeyOfTechnicTagEnum(technicTagEnum));
            //去缓存中热门课程列表
            List<String> list = JSON.parseArray(result, String.class);
            for (String s :list) {
                //首先根据课程列表的值，找到缓存中对应的value
                String courseWithTeacherSTCDTOStr = cache.getString(s);
                CourseWithTeacherSTCDTO courseWithTeacherSTCDTO = JSON.parseObject(courseWithTeacherSTCDTOStr, CourseWithTeacherSTCDTO.class);
                courseWithTeacherSTCDTOS.add(courseWithTeacherSTCDTO);
            }

        return courseWithTeacherSTCDTOS;
    }

}

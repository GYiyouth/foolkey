package foolkey.pojo.root.CAO.CourseTeacher;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.DataStructure.DoubleLink;
import foolkey.pojo.root.DataStructure.Node;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.send_to_client.CourseTeacherPopularDTO;
import foolkey.pojo.root.vo.dto.CourseDTO;
import foolkey.tool.StaticVariable;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by admin on 2017/4/27.
 */
@Component("courseTeacherCAO")
public class CourseTeacherCAO extends AbstractCAO {

    /**
     * OK
     * 添加一门热门课程到缓存中
     * @param courseTeacherPopularDTO
     */
    public void addCourseTeacherPopularDTOToCache(CourseTeacherPopularDTO courseTeacherPopularDTO, TechnicTagEnum technicTagEnum,DirectionEnum directionEnum) {

        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());

        if (technicMap == null) {
            //创建此标签的缓存
            // 缓存还没有这类标签的缓存
            //1. 创建 标签下面的课程map
            DoubleLink courseTeacherPopularDoubleLink = new DoubleLink();
            Map<String, DoubleLink> technicCourseTeacherMap = new HashedMap();
            if(directionEnum==DirectionEnum.head){
                courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
            }else {
                courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
            }
            //2. 把课程的链表添加到map中
            technicCourseTeacherMap.put(courseTeacherToken, courseTeacherPopularDoubleLink);
            //3. 把map添加到缓存的标签token里面
            cache.set(courseTeacherPopularDTO.getCourseTeacherDTO().getTechnicTagEnum().name(), technicCourseTeacherMap);
        } else {
            //缓存里面有此类的标签 例如Java
            if (technicMap.containsKey(courseTeacherToken)) {
                //已经有了“课程”项
                System.out.println("有了课程项");
                DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                //重置
//                courseTeacherPopularDoubleLink.InitMyDoubleLink();
                System.out.println("链表长度：" + courseTeacherPopularDoubleLink.getLength());
                if(directionEnum==DirectionEnum.head){
                    courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
                }else {
                    courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
                }
                System.out.println(courseTeacherPopularDoubleLink.getLength() + "?个");
            } else {
                //没有课程项
                //创建课程项
                System.out.println("没有课程项");
                DoubleLink courseTeacherPopularDoubleLink = new DoubleLink();
                if(directionEnum==DirectionEnum.head){
                    courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
                }else {
                    courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
                }
                technicMap.put(courseTeacherToken, courseTeacherPopularDoubleLink);
            }
        }
    }



    /**
     * OK
     * 添加热门课程到缓存
     * @param courseTeacherPopularDTOS
     * @param technicTagEnum
     * @param directionEnum
     */
    public void addCourseTeacherPopularDTOSToCache(TechnicTagEnum technicTagEnum, ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS, DirectionEnum directionEnum){
        if(technicTagEnum == null || courseTeacherPopularDTOS == null || courseTeacherPopularDTOS.size() == 0 || directionEnum == null){
            throw new NullPointerException("courseTeacherPopularDTOS/directionEnum is null");
        }else{
            System.out.println("类别："+technicTagEnum.name());
            Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
            if (technicMap == null) {
                //缓存还没有这类标签的所有缓存
                //创建此标签的缓存
                //1. 创建 标签下面的课程map
                Map<String, DoubleLink> technicCourseTeacherMap = new HashedMap();
                //2. 课程的双向链表
                DoubleLink courseTeacherPopularDoubleLink = new DoubleLink();
                for (CourseTeacherPopularDTO courseTeacherPopularDTO : courseTeacherPopularDTOS) {
                    System.out.println("热门课程DTO："+courseTeacherPopularDTO);
                    if(directionEnum==DirectionEnum.head){
                        courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
                    }else {
                        courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
                    }
                }
                //3. 把课程的链表添加到map中
                technicCourseTeacherMap.put(courseTeacherToken, courseTeacherPopularDoubleLink);
                //4. 把map添加到缓存的标签token里面
                cache.set(technicTagEnum.name(), technicCourseTeacherMap);
                System.out.println("1.缓存有了"+courseTeacherPopularDoubleLink.getLength() + "个");
            } else {
                //缓存里面有此类的标签 例如Java
                if (technicMap.containsKey(courseTeacherToken)) {
                    //已经有了“课程”项
                    DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                    for (CourseTeacherPopularDTO courseTeacherPopularDTO : courseTeacherPopularDTOS) {
                        System.out.println("热门课程DTO："+courseTeacherPopularDTO);
                        if(directionEnum==DirectionEnum.head){
                            courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
                        }else {
                            courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
                        }
                    }
                    System.out.println("2.缓存有了"+courseTeacherPopularDoubleLink.getLength() + "个");
                } else {
                    //没有课程项
                    //创建课程项
                    DoubleLink courseTeacherPopularDoubleLink = new DoubleLink();
                    for (CourseTeacherPopularDTO courseTeacherPopularDTO : courseTeacherPopularDTOS) {
                        System.out.println("热门课程DTO："+courseTeacherPopularDTO);
                        if(directionEnum==DirectionEnum.head){
                            courseTeacherPopularDoubleLink.addHead(courseTeacherPopularDTO);
                        }else {
                            courseTeacherPopularDoubleLink.addTail(courseTeacherPopularDTO);
                        }
                    }
                    technicMap.put(courseTeacherToken, courseTeacherPopularDoubleLink);
                    System.out.println("3.缓存有了"+courseTeacherPopularDoubleLink.getLength() + "个");
                }
            }

        }
    }

    /**
     * ok
     * 根据技术关键字分类判断缓存是够包含这个门类的课程
     * @param technicTagEnum
     * @return
     */
    public boolean containsCourseTeacher(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //如果请求的超过缓存数目
        if (pageNo * pageSize > StaticVariable.cacheSize) {
            return false;
        }
        System.out.println("技术类别：" + technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
            DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
            //判断当前缓存中是否包含当前页所需要的热门课程
            if (courseTeacherPopularDoubleLink.getLength() >= ((pageNo-1) * pageSize)) {
                return true;
            }
        }
        return false;
    }


    /**
     * OK
     * 缓存中取热门课程
     *
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ArrayList<CourseTeacherPopularDTO> getCourseTeacherPopularDTO(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS = new ArrayList<>();
        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
        ArrayList<Object> tempList = courseTeacherPopularDoubleLink.getNode((pageNo - 1) * pageSize, pageNo * pageSize - 1);
        for (Object object : tempList) {
            CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) object;
            courseTeacherPopularDTOS.add(courseTeacherPopularDTO);
        }
        return courseTeacherPopularDTOS;
    }

    /**
     * OK
     * 更新课程内容之后，同步更新到缓存
     *
     * @param technicTagEnum
     * @param newCourseTeacherPopularDTO
     */
    public void updateCourseTeacherDTO(TechnicTagEnum technicTagEnum, CourseTeacherPopularDTO newCourseTeacherPopularDTO) {

        if(newCourseTeacherPopularDTO == null || technicTagEnum == null){
            throw new NullPointerException("courseTeacherDTO is null");
        }else{
            //获取要修改的课程所在的节点
            Node node = getCourseTeacherPopularNodeByCourseTeacherDTOId(newCourseTeacherPopularDTO.getCourseTeacherDTO().getId());
            if(node != null) {
                CourseTeacherPopularDTO oldCourseTeacherPopularDTO = (CourseTeacherPopularDTO) node.getData();
                //判断是否修改了类别
                if(oldCourseTeacherPopularDTO.getCourseTeacherDTO().getTechnicTagEnum() == technicTagEnum){
                    System.out.println("没有修改技术类别");
                    node.setData(newCourseTeacherPopularDTO);
                    System.out.println("修改");
                }else{
                    // 1. 删除当前类别的当前节点
                    deleteCourseTeacherNode(newCourseTeacherPopularDTO);
                    // 2. 添加到目标类别
                    addCourseTeacherPopularDTOToCache(newCourseTeacherPopularDTO,technicTagEnum,DirectionEnum.head);
                }

            }

        }

        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
        if (technicMap != null && technicMap.containsKey(courseTeacherToken)) {
            DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
            for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                if (courseTeacherPopularDTO.getCourseTeacherDTO().getId() == newCourseTeacherPopularDTO.getCourseTeacherDTO().getId())
                    courseTeacherPopularDoubleLink.getNodeByIndex(i).setData(newCourseTeacherPopularDTO);
                break;
            }
        }
    }

    /**
     * OK
     * 判断缓存中有没有这个id的课程
     * 有：返回courseTeacherPopularDTO 无：返回null
     *
     * @param id
     * @return
     */
    public CourseTeacherPopularDTO getCourseTeacherDTOById(Long id) {
        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
            Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
            if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
                DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                    CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                    if (courseTeacherPopularDTO.getCourseTeacherDTO().getId() == id) {
                        return courseTeacherPopularDTO;
                    }
                }
            }
        }
        return null;
    }


    /**
     * OK
     * 根据id，获取在缓存中的节点
     * @param id
     * @return
     */
    public Node getCourseTeacherPopularNodeByCourseTeacherDTOId(Long  id) {
        if (id == null) {
            throw new NullPointerException("courseTeacherPopularDTO is null");
        } else {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
                if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
                    DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                    for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                        CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        if (courseTeacherPopularDTO.getCourseTeacherDTO().getId() == id){
                            return courseTeacherPopularDoubleLink.getNodeByIndex(i);
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * 获取在热门缓冲区的节点
     * @param aimCourseTeacherDTO
     * @return
     */
//    public Node getCourseTeacherNodeInReserve(CourseTeacherDTO aimCourseTeacherDTO) {
//        if (aimCourseTeacherDTO == null) {
//            throw new NullPointerException("courseTeacherDTO is null");
//        } else {
//            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
//                Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name() + "Reserve");
//                if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
//                    DoubleLink courseTeacherReserveDoubleLink = technicMap.getRewardApplicationDTo(courseTeacherToken);
//                    for (int i = 0; i < courseTeacherReserveDoubleLink.getLength(); i++) {
//                        CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherReserveDoubleLink.getNodeByIndex(i).getData();
//                        if (courseTeacherDTO.getId() == aimCourseTeacherDTO.getId()) {
//                            return courseTeacherReserveDoubleLink.getNodeByIndex(i);
//                        }
//                    }
//                }
//            }
//            return null;
//        }
//    }


    /**
     * OK
     * 根绝CourseTeacherDTO的id，删除缓存中的位置
     *
     * @param aimCourseTeacherPopularDTO
     */
    public void deleteCourseTeacherNode(CourseTeacherPopularDTO aimCourseTeacherPopularDTO) {
        if (aimCourseTeacherPopularDTO == null) {
            throw new NullPointerException("id is null");
        } else {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
                if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
                    DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                    for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                        CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        if (courseTeacherPopularDTO.getCourseTeacherDTO().getId() == aimCourseTeacherPopularDTO.getCourseTeacherDTO().getId()) {
                            System.out.println("删除节点:"+courseTeacherPopularDTO);
                            courseTeacherPopularDoubleLink.delNodeByIndex(i);
                            return;
                        }
                    }
                }
            }
        }
    }

    /**
     * 删除热门缓冲区中的数据
     * @param aimCourseTeacherDTO
     */
//    public void deleteCourseTeacherNodeInReserve(CourseTeacherDTO aimCourseTeacherDTO){
//        if (aimCourseTeacherDTO == null) {
//            throw new NullPointerException("id is null");
//        } else {
//            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
//                Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name()+"Reserve");
//                if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
//                    DoubleLink courseTeacherReserveDoubleLink = technicMap.getRewardApplicationDTo(courseTeacherToken);
//                    for (int i = 0; i < courseTeacherReserveDoubleLink.getLength(); i++) {
//                        CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherReserveDoubleLink.getNodeByIndex(i).getData();
//                        if (courseTeacherDTO.getId() == aimCourseTeacherDTO.getId()) {
//                            courseTeacherReserveDoubleLink.delNodeByIndex(i);
//                            return;
//                        }
//                    }
//                }
//            }
//        }
//    }
    /**
     * 查看某个类别下的课程的缓存的大小
     *
     * @param technicTagEnum
     * @return
     */
    public Integer getTechnicTagCourseTeacherLength(TechnicTagEnum technicTagEnum) {
        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
        if (technicMap != null && technicMap.containsKey(courseTeacherToken)) {
            DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
            return courseTeacherPopularDoubleLink.getLength();
        } else {
            return 0;
        }

    }



    /**
     * OK
     * 获取所有的热门课程
     * @param technicTagEnum
     * @return
     */
    public ArrayList<CourseTeacherPopularDTO> getAllCourseTeacherPopular(TechnicTagEnum technicTagEnum) {
        ArrayList<CourseTeacherPopularDTO> courseTeacherPopularDTOS = new ArrayList<>();
        System.out.println("技术类别：" + technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
        ArrayList<Object> tempList = courseTeacherPopularDoubleLink.getAllNode();
        for (Object object : tempList) {
            CourseTeacherPopularDTO courseTeacherPopularDTO = (CourseTeacherPopularDTO) object;
            courseTeacherPopularDTOS.add(courseTeacherPopularDTO);
        }
        return courseTeacherPopularDTOS;
    }

    /**
     * 自己测试用的
     */
    public void show() {
        for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
            Map<String, DoubleLink> technicMap = cache.getString(technicTagEnum.name());
            if ((technicMap != null) && (technicMap.containsKey(courseTeacherToken))) {
                DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
                for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                    CourseDTO courseTeacherDTO = (CourseDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                    if (courseTeacherDTO.getId() == 123L) {
                        CourseDTO courseTeacherDTO1 = (CourseDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        System.out.println(courseTeacherDTO1.getId() + "====");
                        System.out.println(courseTeacherDTO1.getTopic() + "=======");
                    }
                }
            }
        }
    }

}

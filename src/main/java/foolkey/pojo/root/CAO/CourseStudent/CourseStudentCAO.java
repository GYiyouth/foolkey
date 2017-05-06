package foolkey.pojo.root.CAO.CourseStudent;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.DataStructure.DoubleLink;
import foolkey.pojo.root.DataStructure.Node;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.cacheDTO.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import foolkey.tool.StaticVariable;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ustcg on 2017/5/6.
 */
@Component("courseStudentCAO")
public class CourseStudentCAO extends AbstractCAO {


    /**
     * 添加一门悬赏到缓存
     * @param technicTagEnum
     * @param courseStudentPopularDTO
     * @param directionEnum
     */
    public void addCourseStudentPopularDTOToCache(TechnicTagEnum technicTagEnum, CourseStudentPopularDTO courseStudentPopularDTO, DirectionEnum directionEnum) {

        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());

        if (technicMap == null) {
            //创建此标签的缓存
            // 缓存还没有这类标签的缓存
            //1. 创建 标签下面的悬赏map
            DoubleLink courseStudentPopularDoubleLink = new DoubleLink();
            Map<String, DoubleLink> technicCourseTeacherMap = new HashedMap();
            if(directionEnum==DirectionEnum.head){
                courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
            }else {
                courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
            }
            //2. 把悬赏的链表添加到map中
            technicCourseTeacherMap.put(courseStudentToken, courseStudentPopularDoubleLink);
            //3. 把map添加到缓存的标签token里面
            cache.put(technicTagEnum.name(), technicCourseTeacherMap);
        } else {
            //缓存里面有此类的标签 例如Java
            if (technicMap.containsKey(courseStudentToken)) {
                //已经有了“悬赏”项
                System.out.println("有了悬赏项");
                DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
                //重置
//                courseTeacherPopularDoubleLink.InitMyDoubleLink();
                System.out.println("链表长度：" + courseStudentPopularDoubleLink.getLength());
                if(directionEnum==DirectionEnum.head){
                    courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
                }else {
                    courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
                }
                System.out.println(courseStudentPopularDoubleLink.getLength() + "?个");
            } else {
                //没有悬赏项
                //创建悬赏项
                System.out.println("没有悬赏项");
                DoubleLink courseStudentPopularDoubleLink = new DoubleLink();
                if(directionEnum==DirectionEnum.head){
                    courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
                }else {
                    courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
                }
                technicMap.put(courseStudentToken, courseStudentPopularDoubleLink);
            }
        }
    }



    /**
     * 添加悬赏到缓存区
     * @param technicTagEnum
     * @param courseStudentPopularDTOS
     * @param directionEnum
     */
    public void addCourseStudentPopularDTOSToCache(TechnicTagEnum technicTagEnum, ArrayList<CourseStudentPopularDTO> courseStudentPopularDTOS, DirectionEnum directionEnum){
        if(technicTagEnum == null || technicTagEnum == null || courseStudentPopularDTOS.size() == 0 || directionEnum == null){
            throw new NullPointerException("technicTagEnum/courseStudentPopularDTOS/directionEnum is null");
        }else{
            System.out.println("类别："+technicTagEnum.name());
            Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
            if (technicMap == null) {
                //缓存还没有这类标签的所有缓存
                //创建此标签的缓存
                //1. 创建 标签下面的悬赏map
                Map<String, DoubleLink> technicCourseStudentMap = new HashedMap();
                //2. 悬赏的双向链表
                DoubleLink courseStudentPopularDoubleLink = new DoubleLink();
                for (CourseStudentPopularDTO courseStudentPopularDTO : courseStudentPopularDTOS) {
                    System.out.println("最新的悬赏："+courseStudentPopularDTO);
                    if(directionEnum==DirectionEnum.head){
                        courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
                    }else {
                        courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
                    }
                }
                //3. 把悬赏的链表添加到map中
                technicCourseStudentMap.put(courseStudentToken, courseStudentPopularDoubleLink);
                //4. 把map添加到缓存的标签token里面
                cache.put(technicTagEnum.name(), technicCourseStudentMap);
                System.out.println("1.缓存有了"+courseStudentPopularDoubleLink.getLength() + "个");
            } else {
                //缓存里面有此类的标签 例如Java
                if (technicMap.containsKey(courseStudentToken)) {
                    //已经有了“悬赏”项
                    DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
                    for (CourseStudentPopularDTO courseStudentPopularDTO : courseStudentPopularDTOS) {
                        System.out.println("最新的悬赏："+courseStudentPopularDTO);
                        if(directionEnum==DirectionEnum.head){
                            courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
                        }else {
                            courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
                        }
                    }
                    System.out.println("2.缓存有了"+courseStudentPopularDoubleLink.getLength() + "个");
                } else {
                    //没有"悬赏"项
                    //创建
                    DoubleLink courseStudentPopularDoubleLink = new DoubleLink();
                    for (CourseStudentPopularDTO courseStudentPopularDTO : courseStudentPopularDTOS) {
                        System.out.println("最新的悬赏："+courseStudentPopularDTO);
                        if(directionEnum==DirectionEnum.head){
                            courseStudentPopularDoubleLink.addHead(courseStudentPopularDTO);
                        }else {
                            courseStudentPopularDoubleLink.addTail(courseStudentPopularDTO);
                        }
                    }
                    technicMap.put(courseStudentToken, courseStudentPopularDoubleLink);
                    System.out.println("3.缓存有了"+courseStudentPopularDoubleLink.getLength() + "个");
                }
            }

        }
    }



    /**
     * ok
     * 根据技术关键字分类判断缓存是够包含这个门类的悬赏
     * @param technicTagEnum
     * @return
     */
    public boolean containsCourseStudent(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        //如果请求的超过缓存数目
        if ((pageNo * pageSize) > StaticVariable.cacheSize) {
            return false;
        }
        System.out.println("技术类别：" + technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        if ((technicMap != null) && (technicMap.containsKey(courseStudentToken))) {
            DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
            //判断当前缓存中是否包含当前页所需要的热门课程
            if (courseStudentPopularDoubleLink.getLength() >= ((pageNo-1) * pageSize)) {
                return true;
            }
        }
        return false;
    }

    /**
     * OK
     * 缓存中取最新的缓存
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ArrayList<CourseStudentPopularDTO> getCourseStudentPopularDTO(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize) {
        ArrayList<CourseStudentPopularDTO> courseStudentPopularDTOS = new ArrayList<>();
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含悬赏
        DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
        ArrayList<Object> tempList = courseStudentPopularDoubleLink.getNode((pageNo - 1) * pageSize, pageNo * pageSize - 1);
        for (Object object : tempList) {
            CourseStudentPopularDTO courseStudentPopularDTO = (CourseStudentPopularDTO) object;
            courseStudentPopularDTOS.add(courseStudentPopularDTO);
        }
        return courseStudentPopularDTOS;
    }


    /**
     * 根据悬赏id获取到悬赏与学生信息
     * @param id
     * @return
     */
    public CourseStudentPopularDTO getCourseStudentPopularDTOByCourseStudentId(Long id) {
        if(id == null){
            throw new NullPointerException("id is null");
        }else {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
                if ((technicMap != null) && (technicMap.containsKey(courseStudentToken))) {
                    DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
                    for (int i = 0; i < courseStudentPopularDoubleLink.getLength(); i++) {
                        CourseStudentPopularDTO courseStudentPopularDTO = (CourseStudentPopularDTO) courseStudentPopularDoubleLink.getNodeByIndex(i).getData();
                        if (courseStudentPopularDTO.getCourseStudentDTO().getId() == id) {
                            return courseStudentPopularDTO;
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 根据悬赏id，获取悬赏的内容
     * @param id
     * @return
     */
    public CourseStudentDTO getCourseStudentDTOByCourseStudentId(Long id){
        if(id == null){
            throw new NullPointerException("id is null");
        }else {
            CourseStudentPopularDTO courseStudentPopularDTO = getCourseStudentPopularDTOByCourseStudentId(id);
            if(courseStudentPopularDTO != null){
                return courseStudentPopularDTO.getCourseStudentDTO();
            }
        }
        return null;
    }


    /**
     * 更新缓存中的悬赏信息
     * @param technicTagEnum
     * @param newCourseStudentPopularDTO
     */
    public void updateCourseStudentDTO(TechnicTagEnum technicTagEnum, CourseStudentPopularDTO newCourseStudentPopularDTO) {

        if(newCourseStudentPopularDTO == null || technicTagEnum == null){
            throw new NullPointerException("newCourseStudentPopularDTO is null");
        }else{
            //获取要修改的课程所在的节点
            Node node = getCourseStduentPopularNodeByCourseStudentDTOId(newCourseStudentPopularDTO.getStudentDTO().getId());
            if(node != null) {
                CourseStudentPopularDTO oldCourseStudentPopularDTO = (CourseStudentPopularDTO) node.getData();
                //判断是否修改了类别
                if(oldCourseStudentPopularDTO.getCourseStudentDTO().getTechnicTagEnum() == technicTagEnum){
                    System.out.println("没有修改技术类别");
                    node.setData(newCourseStudentPopularDTO);
                    System.out.println("修改");
                }else{
                    // 1. 删除当前类别的当前节点
                    deleteCourseTeacherNode(newCourseStudentPopularDTO);
                    // 2. 添加到目标类别
                    addCourseStudentPopularDTOToCache(technicTagEnum,newCourseStudentPopularDTO,DirectionEnum.head);
                }

            }

        }
    }


    /**
     * 根据悬赏id，获取在缓存中存储节点
     * @param id
     * @return
     */
    public Node getCourseStduentPopularNodeByCourseStudentDTOId(Long  id) {
        if (id == null) {
            throw new NullPointerException("id is null");
        } else {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
                if ((technicMap != null) && (technicMap.containsKey(courseStudentToken))) {
                    DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
                    for (int i = 0; i < courseStudentPopularDoubleLink.getLength(); i++) {
                        CourseStudentPopularDTO courseStudentPopularDTO = (CourseStudentPopularDTO) courseStudentPopularDoubleLink.getNodeByIndex(i).getData();
                        if (courseStudentPopularDTO.getCourseStudentDTO().getId() == id){
                            return courseStudentPopularDoubleLink.getNodeByIndex(i);
                        }
                    }
                }
            }
            return null;
        }
    }

    /**
     * OK
     * 根据缓存悬赏信息，删除缓存中节点
     * @param aimCourseStudentPopularDTO
     */
    public void deleteCourseTeacherNode(CourseStudentPopularDTO aimCourseStudentPopularDTO) {
        if (aimCourseStudentPopularDTO == null) {
            throw new NullPointerException("aimCourseStudentPopularDTO is null");
        } else {
            for (TechnicTagEnum technicTagEnum : TechnicTagEnum.values()) {
                Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
                if ((technicMap != null) && (technicMap.containsKey(courseStudentToken))) {
                    DoubleLink courseStudentPopularDoubleLink = technicMap.get(courseStudentToken);
                    for (int i = 0; i < courseStudentPopularDoubleLink.getLength(); i++) {
                        CourseStudentPopularDTO courseStudentPopularDTO = (CourseStudentPopularDTO) courseStudentPopularDoubleLink.getNodeByIndex(i).getData();
                        if (courseStudentPopularDTO.getCourseStudentDTO().getId() == aimCourseStudentPopularDTO.getCourseStudentDTO().getId()) {
                            System.out.println("删除节点:"+courseStudentPopularDTO);
                            courseStudentPopularDoubleLink.delNodeByIndex(i);
                            return;
                        }
                    }
                }
            }
        }
    }
}

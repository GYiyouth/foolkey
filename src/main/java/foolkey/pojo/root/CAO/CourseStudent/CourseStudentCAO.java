package foolkey.pojo.root.CAO.CourseStudent;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.DataStructure.DoubleLink;
import foolkey.pojo.root.vo.assistObject.DirectionEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.cacheDTO.CourseStudentPopularDTO;
import foolkey.pojo.root.vo.dto.CourseStudentDTO;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ustcg on 2017/5/6.
 */
@Component("courseStudentCAO")
public class CourseStudentCAO extends AbstractCAO {


    public void addCourseStudentPopularDTOSToCache(TechnicTagEnum technicTagEnum, ArrayList<CourseStudentPopularDTO> courseStudentPopularDTOS, DirectionEnum directionEnum){
        if(technicTagEnum == null || technicTagEnum == null || courseStudentPopularDTOS.size() == 0 || directionEnum == null){
            throw new NullPointerException("technicTagEnum/courseStudentPopularDTOS/directionEnum is null");
        }else{
            System.out.println("类别："+technicTagEnum.name());
            Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
            if (technicMap == null) {
                //缓存还没有这类标签的所有缓存
                //创建此标签的缓存
                //1. 创建 标签下面的课程map
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
}

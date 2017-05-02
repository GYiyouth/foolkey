package foolkey.pojo.root.CAO.CourseTeacher;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.DataStructure.DoubleLink;
import foolkey.pojo.root.DataStructure.Node;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.tool.StaticVariable;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by admin on 2017/4/27.
 */
@Component("courseTeacherCAO")
public class CourseTeacherCAO extends AbstractCAO{


    /**
     * 根据技术关键字分类判断缓存是够包含这个门类的课程
     * @param technicTagEnum
     * @return
     */
    public boolean contaionsCourseTeacher(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        //如果请求的超过缓存数目
        if(pageNo*pageSize > StaticVariable.cacheSize){
            return false;
        }
        System.out.println("技术类别："+technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
            DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
            //判断当前缓存中是否包含当前页所需要的热门课程
            if(courseTeacherPopularDoubleLink.getLength()>=(pageNo*pageSize)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 缓存中取热门课程
     * @param technicTagEnum
     * @param pageNo
     * @param pageSize
     * @return
     */
    public ArrayList<CourseTeacherDTO> getCourseTeacherPopular(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseTeacherDTO> courseTeacherDTOS = new ArrayList<>();
        System.out.println("技术类别："+technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
        ArrayList<Object> tempList = courseTeacherPopularDoubleLink.getNode((pageNo-1)*pageSize, pageNo*pageSize-1);
        for(Object object: tempList){
            CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) object;
            courseTeacherDTOS.add(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }

    /**
     * 添加热门课程到缓存
     * @param technicTagEnum
     * @param courseTeacherDTOS
     */
    public void addCourseTeacherPopular(TechnicTagEnum technicTagEnum,ArrayList<CourseTeacherDTO> courseTeacherDTOS){
//        ArrayList<CourseTeacherDTO> courseTeacherDTOS = BeanFactory.getBean("arrayList",ArrayList.class);
        System.out.println("技术类别："+technicTagEnum.name());
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());

        if(technicMap == null){
            //缓存还没有这类标签的缓存
            //创建此标签的缓存
            //1. 创建 标签下面的课程map
            Map<String, DoubleLink> technicCourseTeacherMap = new HashedMap();
            //2. 课程的双向链表
            DoubleLink courseTeacherPopularDoubleLink =  new DoubleLink();
            for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
            }
            //3. 把课程的链表添加到map中
            technicCourseTeacherMap.put(courseTeacherToken,courseTeacherPopularDoubleLink);
            //4. 把map添加到缓存的标签token里面
            cache.put(technicTagEnum.name(),technicCourseTeacherMap);
        }else {
            //缓存里面有此类的标签 例如Java
            if(technicMap.containsKey(courseTeacherToken)){
                //已经有了“课程”项
                DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                    courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
                }
                System.out.println(courseTeacherPopularDoubleLink.getLength()+"?个");
            }else{
                //没有课程项
                //创建课程项
                DoubleLink courseTeacherPopularDoubleLink =  new DoubleLink();
                for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                    courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
                }
                technicMap.put(courseTeacherToken,courseTeacherPopularDoubleLink);
            }
        }
    }

    /**
     * 自动任务，填满课程缓存区
     * @param technicTagEnum
     * @param courseTeacherDTOS
     */
    public void autoAddCourseTeacherToCache(TechnicTagEnum technicTagEnum, ArrayList<CourseTeacherDTO> courseTeacherDTOS){
        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());

        if(technicMap == null){
            //创建此标签的缓存
            // 缓存还没有这类标签的缓存
            //1. 创建 标签下面的课程map
            DoubleLink courseTeacherPopularDoubleLink =  new DoubleLink();
            Map<String,DoubleLink> technicCourseTeacherMap = new HashedMap();

            for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
            }
            //2. 把课程的链表添加到map中
            technicCourseTeacherMap.put(courseTeacherToken,courseTeacherPopularDoubleLink);
            //3. 把map添加到缓存的标签token里面
            cache.put(technicTagEnum.name(),technicCourseTeacherMap);
        }else {
            //缓存里面有此类的标签 例如Java
            if(technicMap.containsKey(courseTeacherToken)){
                //已经有了“课程”项
                System.out.println("有了课程项");
                DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                //重置
                courseTeacherPopularDoubleLink.InitMyDoubleLink();
                System.out.println("初始化之后的长度："+courseTeacherPopularDoubleLink.getLength());
                for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                    courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
                }
                System.out.println(courseTeacherPopularDoubleLink.getLength()+"?个");
            }else{
                //没有课程项
                //创建课程项
                System.out.println("没有课程项");
                DoubleLink courseTeacherPopularDoubleLink =  new DoubleLink();
                for(CourseTeacherDTO courseTeacherDTO: courseTeacherDTOS){
                    courseTeacherPopularDoubleLink.addTail(courseTeacherDTO);
                }
                technicMap.put(courseTeacherToken,courseTeacherPopularDoubleLink);
            }
        }

    }

    /**
     * 更新课程内容之后，同步更新到缓存
     * @param technicTagEnum
     * @param newCourseTeacherDTOS
     */
    public void updateCourseTeacherDTO(TechnicTagEnum technicTagEnum, CourseTeacherDTO newCourseTeacherDTOS){

        Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        if(technicMap != null && technicMap.containsKey(courseTeacherToken)) {
            DoubleLink courseTeacherPopularDoubleLink = technicMap.get(courseTeacherToken);
            for (int i = 0; i < courseTeacherPopularDoubleLink.getLength(); i++) {
                CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                if (courseTeacherDTO.getId() == newCourseTeacherDTOS.getId())
                    courseTeacherPopularDoubleLink.getNodeByIndex(i).setData(newCourseTeacherDTOS);
                break;
            }
        }
    }

    /**
     * 判断缓存中有没有这个id的课程
     * 有：返回courseTeacherDTO  无：返回null
     * @param id
     * @return
     */
    public CourseTeacherDTO getCourseTeacherDTOById(Long id){
        for(TechnicTagEnum technicTagEnum:TechnicTagEnum.values()){
            Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
            if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
                DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                for(int i = 0;i<courseTeacherPopularDoubleLink.getLength();i++){
                    CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                    if(courseTeacherDTO.getId() == id){
                        return courseTeacherDTO;
                    }
                }
            }
        }
        return null;
    }


    /**
     * 根据id，获取在缓存中的节点
     * @param id
     * @return
     */
    public Node getCourseTeacherNode(Long id){
        if(id == null){
            throw new NullPointerException("id is null");
        }else{
            for(TechnicTagEnum technicTagEnum:TechnicTagEnum.values()){
                Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
                if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
                    DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                    for(int i = 0;i<courseTeacherPopularDoubleLink.getLength();i++){
                        CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        if(courseTeacherDTO.getId() == id){
                            return courseTeacherPopularDoubleLink.getNodeByIndex(i);
                        }
                    }
                }
            }
            return null;
        }
    }


    /**
     * 根绝CourseTeacherDTO的id，删除缓存中的位置
     * @param id
     */
    public void deleteCourseTeacherNode(Long id){
        if(id == null){
            throw new NullPointerException("id is null");
        }else{
            for(TechnicTagEnum technicTagEnum:TechnicTagEnum.values()){
                Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
                if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
                    DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                    for(int i = 0;i<courseTeacherPopularDoubleLink.getLength();i++){
                        CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        if(courseTeacherDTO.getId() == id){
                            courseTeacherPopularDoubleLink.delNodeByIndex(i);
                            return;
                        }
                    }
                }
            }
        }
    }



    /**
     * 自己测试用的
     */
    public void show(){
        for(TechnicTagEnum technicTagEnum:TechnicTagEnum.values()){
            Map<String, DoubleLink> technicMap = cache.getMap(technicTagEnum.name());
            if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
                DoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
                for(int i = 0;i<courseTeacherPopularDoubleLink.getLength();i++){
                    CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                    if(courseTeacherDTO.getId() == 123L){
                        CourseTeacherDTO courseTeacherDTO1 = (CourseTeacherDTO) courseTeacherPopularDoubleLink.getNodeByIndex(i).getData();
                        System.out.println(courseTeacherDTO1.getId()+"====");
                        System.out.println(courseTeacherDTO1.getTopic()+"=======");
                    }
                }
            }
        }
//        return null;
    }
}

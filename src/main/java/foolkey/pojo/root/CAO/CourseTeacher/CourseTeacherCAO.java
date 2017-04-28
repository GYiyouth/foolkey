package foolkey.pojo.root.CAO.CourseTeacher;

import foolkey.pojo.root.CAO.base.AbstractCAO;
import foolkey.pojo.root.DataStructure.MyDoubleLink;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.CourseTeacherDTO;
import foolkey.tool.BeanFactory;
import foolkey.tool.StaticVariable;
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
        Map<String, MyDoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        if((technicMap != null) && (technicMap.containsKey(courseTeacherToken))){
            MyDoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
            //判断当前缓存中是否包含当前页所需要的热门课程
            if(courseTeacherPopularDoubleLink.getLength()>(pageNo*pageSize)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<CourseTeacherDTO> getCourseTeacherPopular(TechnicTagEnum technicTagEnum, Integer pageNo, Integer pageSize){
        ArrayList<CourseTeacherDTO> courseTeacherDTOS = BeanFactory.getBean("arrayList",ArrayList.class);
        System.out.println("技术类别："+technicTagEnum.name());
        Map<String, MyDoubleLink> technicMap = cache.getMap(technicTagEnum.name());
        //有这个标签的东西，而且包含热门课程
        MyDoubleLink courseTeacherPopularDoubleLink =  technicMap.get(courseTeacherToken);
        ArrayList<Object> tempList = courseTeacherPopularDoubleLink.getNode((pageNo-1)*pageSize, pageNo*pageSize-1);
        for(Object object: tempList){
            CourseTeacherDTO courseTeacherDTO = (CourseTeacherDTO) object;
            courseTeacherDTOS.add(courseTeacherDTO);
        }
        return courseTeacherDTOS;
    }
}

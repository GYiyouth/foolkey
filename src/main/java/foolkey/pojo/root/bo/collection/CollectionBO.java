package foolkey.pojo.root.bo.collection;

import foolkey.pojo.root.DAO.collection_course.GetCollectionCourseDAO;
import foolkey.pojo.root.vo.dto.CollectionCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by ustcg on 2017/5/8.
 */
@Service("collectionBO")
public class CollectionBO {

    @Autowired
    private GetCollectionCourseDAO getCollectionCourseDAO;

    /**
     * 分页获取收集的课程
     * @param studentId
     * @param pageNo
     * @param pageSize
     * @return
     * @throws Exception
     */
    public ArrayList<CollectionCourseDTO> getMyCollectionCourseDTOS(Long studentId, Integer pageNo, Integer pageSize) throws Exception{
        String hql = "select cc from CollectionCourseDTO cc where cc.userId = ?";
        return getCollectionCourseDAO.findByPage(hql,pageNo,pageSize,studentId);
    }



}

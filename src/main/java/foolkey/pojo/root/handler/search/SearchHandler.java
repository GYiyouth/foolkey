package foolkey.pojo.root.handler.search;

import foolkey.pojo.root.bo.AbstractBO;
import foolkey.pojo.root.bo.search.*;
import foolkey.pojo.root.vo.assistObject.CourseTimeDayEnum;
import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索，明文，用json传输，需要带上 keyWord，condition, pageNo
 * condition有：
 * course - 课程
 * reward - 悬赏
 * article - 文章
 * teacher - 老师
 * question - 提问
 * Created by geyao on 2017/5/2.
 */
@Service
@Transactional(readOnly = true)
public class SearchHandler extends AbstractBO{

    @Autowired
    private SearchArticleBO searchArticleBO;
    @Autowired
    private SearchCourseBO searchCourseBO;
    @Autowired
    private SearchQuestionBO searchQuestionBO;
    @Autowired
    private SearchRewardBO searchRewardBO;
    @Autowired
    private SearchTeacherBO searchTeacherBO;

    public void execute(
            HttpServletRequest request,
            HttpServletResponse response,
            JSONObject jsonObject
    )throws Exception{
        String clearText = request.getParameter("clearText");
        JSONObject clearJSON = JSONObject.fromObject(clearText);

        String input = clearJSON.getString("keyWord");
        String condition = clearJSON.getString("condition");
        String page = clearJSON.getString("pageNo");

        Integer pageNo = Integer.parseInt(page);

        //分割关键词
        String[] keyWords = input.split(" |,|\\.|,");
        //分析关键词，提取技术标签
        ArrayList<String> keyList = new ArrayList<>();
        ArrayList<TechnicTagEnum> techList = new ArrayList<>();
        ArrayList<CourseTimeDayEnum> timeList = new ArrayList<>();
        for (String key : keyWords){
            try {
                TechnicTagEnum tagEnum = TechnicTagEnum.valueOf(key);
                techList.add(tagEnum);
            }catch (Exception e){
                try {
                    CourseTimeDayEnum timeDayEnum = CourseTimeDayEnum.valueOf(key);
                    timeList.add(timeDayEnum);
                }catch (Exception e1){
                    keyList.add(key);
                }
            }

        }
        //分析结束后，keyList包含文字关键词，techList是技术标签

        List result = new ArrayList();
        switch (condition){
            case "course": {
                result = searchCourseBO.searchCourseDTO(keyList, techList,timeList, pageNo);
            }break;
            case "reward" :{
                result = searchRewardBO.searchRewardDTO(keyList, techList, pageNo);
            }break;
            case "article":{
                result = searchArticleBO.searchArticle(keyList, techList, pageNo);
            }break;
            case "teacher":{
                result = searchTeacherBO.searchTeacher(keyList, techList, pageNo);
            }break;
            case "question":{
                result = searchQuestionBO.searchQuestionDTO(keyList, techList, pageNo);
            }break;
            default:{

            }
        }


    }
}

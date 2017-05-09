package foolkey.controller.GR_test;

import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Created by GR on 2017/5/3.
 */
@Component
public class TestG3 {
    public static void main(String[] args) throws Exception {
//        System.out.println(Time.getDate());
//        StudentDTO studentDTO = new StudentDTO();
//        StudentDTO aimStudentDTO = new StudentDTO();
//        studentDTO.setPassWord("1221");
//        studentDTO.myClone(aimStudentDTO,studentDTO);
//        System.out.println(aimStudentDTO.getPassWord()+"?");
        //类别为空，则随机选取一个类别
//        if(studentDTO.getTechnicTagEnum() == null){
            Random random = new Random();
            Integer technicSize = TechnicTagEnum.values().length;
            System.out.println("技术类别中的数量:"+technicSize);

            Integer temp = random.nextInt(technicSize);
            TechnicTagEnum technicTagEnum = TechnicTagEnum.values()[temp];
        System.out.println(technicTagEnum);
//        }
    }
}

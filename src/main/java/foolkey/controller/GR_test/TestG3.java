package foolkey.controller.GR_test;

import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.springframework.stereotype.Component;

/**
 * Created by GR on 2017/5/3.
 */
@Component
public class TestG3 {
    public static void main(String[] args) throws Exception {
//        System.out.println(Time.getDate());
        StudentDTO studentDTO = new StudentDTO();
        StudentDTO aimStudentDTO = new StudentDTO();
        studentDTO.setPassWord("1221");
        studentDTO.myClone(aimStudentDTO,studentDTO);
        System.out.println(aimStudentDTO.getPassWord()+"?");
    }
}

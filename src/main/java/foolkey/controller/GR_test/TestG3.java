package foolkey.controller.GR_test;

import foolkey.pojo.root.vo.assistObject.TechnicTagEnum;
import foolkey.pojo.root.vo.dto.StudentDTO;
import foolkey.tool.Time;
import org.springframework.stereotype.Component;
import sun.applet.Main;

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
//            Random random = new Random();
//            Integer technicSize = TechnicTagEnum.values().length;
//            System.out.println("技术类别中的数量:"+technicSize);
//
//            Integer temp = random.nextInt(technicSize);
//            TechnicTagEnum technicTagEnum = TechnicTagEnum.values()[temp];
//        System.out.println(technicTagEnum);
//        }

        TestG3 testG3 = new TestG3();
        testG3.show(4,5,6,7,8,9);



    }


    public void show(int i,Object... params){

        System.out.println("show...");
        System.out.println(i+"?");
        show2(i,params);
    }

    public void show2(int j,Object... params){
        System.out.println("?");
        for(int i =0, len = params.length;i<len;i++){
            System.out.println("i"+i);
        }
    }

}

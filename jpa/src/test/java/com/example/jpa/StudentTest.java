package com.example.jpa;

import com.example.jpa.po.StudentPO;
import com.example.jpa.repository.StudentRepository;
import com.example.jpa.service.StudentService;
import com.example.jpa.serviceImpl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTest {

    @Autowired
    private StudentServiceImpl service;

    @Test
    public void changeTest() {
        Integer result;
        StudentPO po1 = new StudentPO();
        po1.setStudentName("卡卡西");
        LocalDateTime localDateTime = LocalDateTime.of(1969, 6, 16, 14, 36);
        po1.setBirthday(localDateTime);

        StudentPO po2 = service.insert(po1);
        System.out.println("新增用户：" + po2);

        result = service.updateBirthdayByCourseID(localDateTime.minusYears(2), po2.getStudentID());
        if (result == null) {
            System.out.println("修改用户失败！");
        } else {
            StudentPO po = service.selectByCourseID(po2.getStudentID());
            System.out.println("修改用户：" + po);
        }

        result=service.delete(po2.getStudentID());
        if (result == null) {
            System.out.println("删除用户失败！");
        } else {
            System.out.println("删除用户个数：" + result);
        }
    }
}

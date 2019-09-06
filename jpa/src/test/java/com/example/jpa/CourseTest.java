package com.example.jpa;

import com.example.jpa.po.CoursePO;
import com.example.jpa.repository.CourseRepository;
import com.example.jpa.serviceImpl.CourseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTest {

    @Autowired
    private CourseServiceImpl service;


    @Test
    public void selectTest() {
        List<CoursePO> coursePOList = service.findAll();
        for (CoursePO po : coursePOList) {
            System.out.println(po);
        }
    }

    @Test
    public void changeTest() {
        CoursePO po1 = new CoursePO();
        po1.setCode("physical");
        po1.setName("物理");

        CoursePO po2 = service.save(po1);
        System.out.println("新增课程：" + po2);

        po2.setName("自然");
        CoursePO po3 = service.save(po2);
        System.out.println("修改课程：" + po3);

        service.delete(po3);
        System.out.println("删除课程：" + po3);

        CoursePO po4 = service.findByCourseID(po3.getCourseID());
        System.out.println("查询课程：" + po4);
    }
}

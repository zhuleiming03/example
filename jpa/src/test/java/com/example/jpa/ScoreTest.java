package com.example.jpa;

import com.example.jpa.po.CoursePO;
import com.example.jpa.po.ScorePO;
import com.example.jpa.po.StudentPO;
import com.example.jpa.serviceImpl.CourseServiceImpl;
import com.example.jpa.serviceImpl.ScoreServiceImpl;
import com.example.jpa.serviceImpl.StudentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScoreTest {

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private CourseServiceImpl courseService;

    @Autowired
    private ScoreServiceImpl service;

    @Test
    public void queryTest() {
        List<ScorePO> scorePOList = service.selectAll();

        for (ScorePO po : scorePOList) {
            System.out.println(po);
        }
    }

    @Test
    public void changeTest() {

        List<StudentPO> studentPOList = studentService.selectAll();
        List<CoursePO> coursePOList = courseService.findAll();

        ScorePO po1 = new ScorePO();
        po1.setScore(86.6f);
        int index = 0;
        if (studentPOList == null || studentPOList.size() == 0) {
            System.out.println(">>未找到学生信息！");
            return;
        } else {
            index = studentPOList.size() - 1;
            po1.setStudentID(studentPOList.get(index).getStudentID());
        }
        if (coursePOList == null || coursePOList.size() == 0) {
            System.out.println(">>未找到课程信息！");
            return;
        } else {
            index = coursePOList.size() - 1;
            po1.setCourseID(coursePOList.get(index).getCourseID());
        }

        System.out.println(">>初始化成绩信息：" + po1);

        ScorePO po2 = service.insert(po1);
        System.out.println(">>新增成绩信息：" + po2);

        Integer result = service.updateScoreByScoreID(90.5f, po2.getScoreID());
        if (result == null) {
            System.out.println(">>修改成绩信息失败：");
        } else {
            System.out.println(">>修改成绩信息条数：" + result);
        }

        ScorePO po3 = service.selectByScoreID(po2.getScoreID());
        System.out.println(">>查询成绩信息：" + po3);

        result = service.delete(po3.getScoreID());
        if (result == null) {
            System.out.println(">>删除成绩信息失败：");
        } else {
            System.out.println(">>删除成绩信息条数：" + result);
        }
    }
}

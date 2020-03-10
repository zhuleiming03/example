package example.spring;

import example.spring.domain.Manager;
import example.spring.domain.Student;
import example.spring.domain.Teacher;
import example.spring.domain.Visitor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Bootstrap {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("rootContextConfiguration.xml");

        System.out.println("\n-----------------------Set()注入-----------------------");
        Student student = (Student) context.getBean("student");
        student.print();

        System.out.println("\n-----------------------构造器注入-----------------------");
        Teacher teacher = (Teacher) context.getBean("teacher");
        teacher.print();

        System.out.println("\n-----------------------静态工厂的方法注入-----------------------");
        Manager manager = (Manager) context.getBean("manager");
        manager.print();

        System.out.println("\n-----------------------实例工厂的方法注入-----------------------");
        Visitor visitor = (Visitor) context.getBean("visitor");
        visitor.print();

    }

}

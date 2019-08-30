package com.example.java;

import com.example.java.domain.Bicycle;
import com.example.java.domain.Car;
import com.example.java.domain.Customer;
import com.example.java.domain.Traffic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CloneClassTest {

    /**
     * 继承测试
     */
    @Test
    public void CustomerTest() {

        Customer customerA = new Customer("X00515");
        System.out.println(customerA);

        Traffic bicycleA = new Bicycle();
        ((Bicycle) bicycleA).setCalorie(105.2f);
        bicycleA.setPay(566.51f);
        bicycleA.setType("Bicycle");
        customerA.setTraffic(bicycleA);

        System.out.println(customerA);

        Traffic carB = new Car();
        carB.setType("Car");
        carB.setPay(129999.99f);
        ((Car) carB).setPlateNumber("FHD592163");
        customerA.setTraffic(carB);

        System.out.println(customerA);
    }

    /**
     * 引用传递测试
     */
    @Test
    public void BicycleTest() {

        Traffic bicycleA = new Bicycle();
        ((Bicycle) bicycleA).setCalorie(105.2f);
        bicycleA.setPay(499.99f);
        bicycleA.setType("Bicycle");
        System.out.println("BicycleA:" + bicycleA);

        Bicycle bicycleB = (Bicycle) bicycleA;
        bicycleB.setCalorie(91.5f);
        System.out.println("BicycleB:" + bicycleB);
        System.out.println("BicycleA:" + bicycleA);
    }

    /**
     * 浅克隆测试
     */
    @Test
    public void CustomerCloneTest() {

        Customer customerC = new Customer("X00651");
        Traffic bicycleC = new Bicycle();
        ((Bicycle) bicycleC).setCalorie(18.5f);
        bicycleC.setPay(199.99f);
        bicycleC.setType("Bicycle");
        customerC.setTraffic(bicycleC);

        try {
            Customer customerD = customerC.clone();
            System.out.println("customerC:" + customerC);
            System.out.println("customerD:" + customerD);

            System.out.println("alter customerD value:");
            customerD.setCustomerID("X00782");
            //customerD.getTraffic().setPay(156.61f);

            System.out.println("customerC:" + customerC);
            System.out.println("customerD:" + customerD);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

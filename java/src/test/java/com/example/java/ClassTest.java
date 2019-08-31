package com.example.java;

import com.example.java.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClassTest {

    /**
     * 继承测试
     */
    @Test
    public void customerTest() {

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
    public void bicycleTest() {

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
     * 克隆测试
     * 字段 单独开辟空间 eg: CustomerID
     * 对象 浅克隆 复制引用的值 eg: Traffic
     * 对象 深克隆 克隆对象 在堆中单独开辟空间 eg: Wallet
     */
    @Test
    public void customerCloneTest() {

        Customer customerC = new Customer("X00651");
        Traffic bicycleC = new Bicycle();
        ((Bicycle) bicycleC).setCalorie(18.5f);
        bicycleC.setPay(199.99f);
        bicycleC.setType("Bicycle");
        Wallet walletC = new Wallet();
        walletC.setPayCount(9);
        customerC.setTraffic(bicycleC);
        customerC.setWallet(walletC);

        try {
            System.out.println("customerD 为 customerC 的克隆体");
            Customer customerD = customerC.clone();
            System.out.println("customerC:" + customerC);
            System.out.println("customerD:" + customerD);

            System.out.println("customerC 分别修改对象Traffic、Wallet和字段CustomerID");
            customerC.setCustomerID("X00650");
            customerC.getTraffic().setPay(99.80f);
            customerC.getWallet().setPayCount(1);
            System.out.println("customerC:" + customerC);
            System.out.println("customerD:" + customerD);
            System.out.println("customerD：Wallet和字段CustomerID 为克隆部分 不改变，Traffic 为引用 跟随改变");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

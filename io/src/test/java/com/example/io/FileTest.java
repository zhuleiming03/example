package com.example.io;

import com.example.io.serviceImpl.FileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Autowired
    private FileServiceImpl service;

    /**
     * 获取当前路径目录
     */
    @Test
    public void getCurrentDir() throws IOException {
        // 第一种：获取类加载的根路径   D:\git\daotie\daotie\target\classes
        File f1 = new File(this.getClass().getResource("/").getPath());
        System.out.println(">>1：" + f1);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录  D:\git\daotie\daotie\target\classes\my
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(">>1：" + f2);

        // 第二种：获取项目路径    D:\git\daotie\daotie
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(">>2：" + courseFile);

        // 第三种：  file:/D:/git/daotie/daotie/target/classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(">>3：" + xmlpath);

        // 第四种： D:\git\daotie\daotie
        System.out.println(">>4：" + System.getProperty("user.dir"));
        /*
         * 结果： C:\Documents and Settings\Administrator\workspace\projectName
         * 获取当前工程路径
         */

        // 第五种：  获取所有的类路径 包括jar包的路径
        System.out.println(">>5：" + System.getProperty("java.class.path"));
    }


    /**
     * 创建目录
     */
    @Test
    public void mkDirectoryTest() {
        String path = System.getProperty("user.dir") + "\\testDir";
        if (service.mkDirectory(path)) {
            System.out.println("创建目录成功");
        } else {
            System.out.println("创建目录失败，目录：" + path);
        }
    }

    /**
     * 创建文件
     */
    @Test
    public void createFileTest() {
        String path = System.getProperty("user.dir") + "\\testDir";
        String fileName = "fdsa42";
        try {
            OutputStream outputStream = service.createFile(path, fileName);
            if (outputStream == null) {
                System.out.println("创建文件失败");
            } else {
                System.out.println("创建文件成功");
                outputStream.close();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
package com.example.java;

import com.example.java.implement.IOServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Test
    public void mkDirectoryTest() {
        String path = "";
        if (IOServiceImpl.mkDirectory(path)) {
            System.out.println("创建目录成功");
        } else {
            System.out.println("创建目录失败，目录：" + path);
        }
    }

    @Test
    public void createFileTest() {
        String path = "D:/FTP/aa/cc";
        String fileName = "fdsa42";
        try {
            OutputStream outputStream = IOServiceImpl.createFile(path, fileName);
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

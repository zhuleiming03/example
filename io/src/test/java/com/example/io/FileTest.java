package com.example.io;

import com.example.io.service.FileService;
import com.example.io.serviceImpl.FileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.OutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Autowired
    private FileServiceImpl service;

    @Test
    public void mkDirectoryTest() {
        String path = "";
        if (service.mkDirectory(path)) {
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
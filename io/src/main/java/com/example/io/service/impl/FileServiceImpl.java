package com.example.io.service.impl;

import com.example.io.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    private static final int BYTE_SIZE = 10;

    @Nullable
    public boolean mkDirectory(String path) {
        File dir = new File(path);
        if (dir.isDirectory()) {
            return true;
        } else {
            return dir.mkdirs();
        }
    }

    @Nullable
    public OutputStream createFile(String path, String fileName) throws FileNotFoundException {
        if (StringUtils.isEmpty(path)) {
            return null;
        }
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }
        File dir = new File(path);
        if (!dir.isDirectory()) {
            if (dir.mkdirs()) {
                log.info("Directory creation failed : " + path);
            }
        }

        return new FileOutputStream(String.format("%s/%s", path, fileName));
    }

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     * @param fileName
     * @return
     * @throws FileNotFoundException
     * @throws IOException
     */
    public InputStream readFileByBytes(String fileName) {

        // 一次读多个字节
        byte[] byteBlock = new byte[BYTE_SIZE];
        int byteRead = 0;

        try {
            InputStream inputStream = new FileInputStream(fileName);

            // 读入多个字节到字节数组中，byteRead为一次读入的字节数
            while ((byteRead = inputStream.read(byteBlock)) != -1) {
                log.info("byteRead:" + byteRead);
            }

            return inputStream;

        } catch (FileNotFoundException e) {
            log.error(e.toString());
            return null;
        } catch (IOException e) {
            log.error(e.toString());
            return null;
        }
    }

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件
     */
    public static void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            System.out.println("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            System.out.println("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉\r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != '\r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == '\r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件
     */
    public static void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                System.out.println("line " + line + ": " + tempString);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
}

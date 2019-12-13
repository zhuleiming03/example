package example.java.io;

import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.TimeUnit;

public class FileTest {

    @Test
    public void readFileTxtTest() {

        String filePath, result;

        System.out.println("以行为单位读取文件");
        filePath = System.getProperty("user.dir") + "\\localFileDir\\read\\txt\\txt2.txt";
        result = FileServlet.readFileByChar(filePath);
        assert result != null;
        System.out.println(result);

        System.out.println("以字符为单位读取文件");
        filePath = System.getProperty("user.dir") + "\\localFileDir\\read\\txt\\txt1.txt";
        result = FileServlet.readFileByLine(filePath);
        assert result != null;
        System.out.println(result);

        System.out.println("以字节为单位读取文件");
        filePath = System.getProperty("user.dir") + "\\localFileDir\\read\\img\\image.jpg";
        byte[] img = FileServlet.readFileByBytes(filePath);
        assert img != null;
        System.out.println(img.length);

    }

    @Test
    public void writeFileTxtTest () throws IOException {
        String path = System.getProperty("user.dir") + "\\localFileDir\\write\\txt";

        StringBuilder contentStringBuilder = new StringBuilder();
        for (int i = 0; i < 3000000; i++) {
            contentStringBuilder.append("I Want to Eat Your Pancreas|君の膵臓をたべたい|我想吃掉你的胰脏|");
            contentStringBuilder.append(i);
            contentStringBuilder.append("\r\n");
        }

        boolean result = false;

        System.out.println("FileOutputStream：通过字节写入数据");
        StopWatch stopwatch = StopWatch.createStarted();
        result = FileServlet.writeFileByFileOutputStream(path, "FileOutputStream.txt",
                contentStringBuilder.toString().getBytes());
        stopwatch.stop();
        System.out.println("create FileOutputStream.txt need time:" + stopwatch.getTime(TimeUnit.MILLISECONDS));
        assert result;

        System.out.println("OutputStreamWriter：通过字符写入数据,可指定编码格式");
        stopwatch.reset();
        stopwatch.start();
        result = FileServlet.writeFileByOutputStreamWriter(path, "OutputStreamWriter.txt",
                contentStringBuilder.toString());
        stopwatch.stop();
        System.out.println("create OutputStreamWriter.txt need time:" + stopwatch.getTime(TimeUnit.MILLISECONDS));
        assert result;

        System.out.println("BufferedWriter：通过缓存写入字符数据，可指定编码格式");
        stopwatch.reset();
        stopwatch.start();
        result = FileServlet.writeFileByBufferedWriter(path, "BufferedWriter.txt",
                contentStringBuilder.toString());
        stopwatch.stop();
        System.out.println("create BufferedWriter.txt need time:" + stopwatch.getTime(TimeUnit.MILLISECONDS));
        assert result;

        System.out.println("FileWriter：通过缓存写入字符数据，不可指定编码格式");
        stopwatch.reset();
        stopwatch.start();
        result = FileServlet.writeFileByFileWriter(path, "FileWriter.txt",
                contentStringBuilder.toString());
        stopwatch.stop();
        System.out.println("create FileWriter.txt need time:" + stopwatch.getTime(TimeUnit.MILLISECONDS));
        assert result;
    }
}

package example.java.io;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class PathTest {

    /**
     * 获取当前路径目录
     */
    @Test
    public void getCurrentDir() throws IOException {
        // 第一种：获取类加载的根路径
        // D:\Demo\example\java\io\target\test-classes
        File f1 = new File(this.getClass().getResource("/").getPath());
        System.out.println(">>1：" + f1);

        // 获取当前类的所在工程路径; 如果不加“/”  获取当前类的加载目录
        // D:\Demo\example\java\io\target\test-classes\example\java\io
        File f2 = new File(this.getClass().getResource("").getPath());
        System.out.println(">>1：" + f2);

        // 第二种：获取项目路径
        // D:\Demo\example\java\io
        File directory = new File("");// 参数为空
        String courseFile = directory.getCanonicalPath();
        System.out.println(">>2：" + courseFile);

        // 第三种：
        // file:/D:/Demo/example/java/io/target/test-classes/
        URL xmlpath = this.getClass().getClassLoader().getResource("");
        System.out.println(">>3：" + xmlpath);

        // 第四种： 获取当前工程路径
        // D:\Demo\example\java\io
        System.out.println(">>4：" + System.getProperty("user.dir"));

        // 第五种：  获取所有的类路径 包括jar包的路径
        // C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.3\lib\idea_rt.jar;
        // C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.3\plugins\junit\lib\junit-rt.jar;
        // C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.3\plugins\junit\lib\junit5-rt.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\charsets.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\deploy.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\access-bridge-64.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\cldrdata.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\dnsns.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\jaccess.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\jfxrt.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\localedata.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\nashorn.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\sunec.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\sunjce_provider.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\sunmscapi.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\sunpkcs11.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\ext\zipfs.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\javaws.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\jce.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\jfr.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\jfxswt.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\jsse.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\management-agent.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\plugin.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\resources.jar;
        // C:\Program Files\Java\jdk1.8.0_20\jre\lib\rt.jar;
        // D:\Demo\example\java\io\target\test-classes;
        // D:\Demo\example\java\io\target\classes;
        // C:\Users\Administrator\.m2\repository\junit\junit\4.12\junit-4.12.jar;
        // C:\Users\Administrator\.m2\repository\org\hamcrest\hamcrest-core\1.3\hamcrest-core-1.3.jar;
        // C:\Program Files\JetBrains\IntelliJ IDEA 2018.3.3\lib\idea_rt.jar
        System.out.println(">>5：" + System.getProperty("java.class.path"));
    }
}

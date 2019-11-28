import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date 已过时，jdk 小于 1.8 时使用
 */
public class DateTest {

    /**
     * 获取时间的属性
     */
    @Test
    public void initDateTest() {

        //1、创建一个当前时间的Date对象
        Date now = new Date();
        System.out.println(">>当前时间:" + now);

        //2、创建一个代表2014年6月12号的Date对象
        Date old = new Date(2014 - 1900, 6 - 1, 12, 12, 56, 10);
        System.out.println(">>指定时间:" + old);

        //3、正确获得一个date对象所包含的信息
        //获得年份 （注意年份要加上1900，这样才是日期对象d2所代表的年份）
        int year = old.getYear() + 1900;
        System.out.println(">>:年份" + year);

        //获得月份  （注意月份要加1，这样才是日期对象d2所代表的月份）
        int month = old.getMonth() + 1;
        System.out.println(">>:月份" + month);

        //获得日期
        int date = old.getDate();
        System.out.println(">>:日期" + date);

        //获得小时
        int hour = old.getHours();//不设置默认为0
        System.out.println(">>:小时" + hour);

        //获得分钟
        int minute = old.getMinutes();
        System.out.println(">>:分钟" + minute);

        //获得秒
        int second = old.getSeconds();
        System.out.println(">>:秒" + second);

        //获得星期 （注意：0代表星期日、1代表星期1、2代表星期2，其他的一次类推了）
        int day = old.getDay();
        System.out.println(">>:星期" + day);
    }

    /**
     * 格式化输出日期时间
     */
    @Test
    public void dateToString() {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println(">>当前时间：" + df.format(date));
    }
}

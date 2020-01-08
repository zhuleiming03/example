package example.java;

import java.io.PrintWriter;
import java.io.StringWriter;

final class CatchMethod {

    void catchTest() {

        Integer result = 0;

        System.out.println(">>0:无异常捕获");
        try {
            result = math0();
        } catch (Exception e) {
            printError(e);
        }
        System.out.println("-------------------------------------");

        System.out.println(">>1:异常捕获不丢出，程序继续执行");
        try {
            result = math1();
        } catch (Exception e) {
            printError(e);
        }
        System.out.println("-------------------------------------");

        System.out.println(">>2:异常捕获丢出，重定义错误内容，丢失错误堆栈信息");
        try {
            result = math2();
        } catch (Exception e) {
            printError(e);
        }

        System.out.println("result:" + result);
    }


    private Integer math2() throws ArithmeticException {
        try {
            return division(89, 0);
        } catch (ArithmeticException e) {
            throw new ArithmeticException(" b is zero");
        }
    }

    private Integer math1() throws ArithmeticException {
        Integer result = 0;
        try {
            result = division(89, 0);
        } catch (ArithmeticException e) {
            printError(e);
        }
        System.out.println("program can go on... ");
        return result;
    }

    private Integer math0() throws ArithmeticException {
        Integer result = division(89, 0);
        System.out.println("program can go on... ");
        return result;
    }

    private Integer division(Integer a, Integer b) {
        return a / b;
    }

    private void printError(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        System.out.println(stringWriter);
    }
}

package example.java.reflect.instance;

import example.java.reflect.domain.Book;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectBook {

    private final static String CLASS_NAME = "example.java.reflect.domain.Book";

    /**
     * 创建对象
     */
    public static void reflectNewInstance() {
        try {
            Class<?> classBook = Class.forName(CLASS_NAME);
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("天气之子");
            book.setAuthor("新海诚");
            System.out.println("reflectNewInstance book = " + book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射私有的构造方法
     */
    public static void reflectPrivateConstructor() {
        try {
            Class<?> classBook = Class.forName(CLASS_NAME);
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class, String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("你的名字", "新海诚");
            Book book = (Book) objectBook;
            System.out.println("reflectPrivateConstructor book = " + book.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射私有属性
     */
    public static void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName(CLASS_NAME);
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("秒速五厘米");
            Field fieldTag = classBook.getDeclaredField("name");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            System.out.println("reflectPrivateField name = " + tag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射私有方法
     */
    public static void reflectPrivateMethod() {
        try {
            Class<?> classBook = Class.forName(CLASS_NAME);
            Method methodBook = classBook.getDeclaredMethod("declaredMethod", int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook, 0);

            System.out.println("reflectPrivateMethod method result = " + string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

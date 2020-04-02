package example.springframework.boot.autoconfigure.conditional.classes.service;

public interface Formatter {

    /**
     * 格式化操作
     *
     * @param object 待格式化对象
     * @return 返回格式化后的内容
     */
    String format(Object object);
}

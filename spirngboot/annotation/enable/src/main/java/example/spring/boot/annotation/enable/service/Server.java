package example.spring.boot.annotation.enable.service;

public interface Server {

    /**
     * 启动服务器
     */
    void start();

    /**
     * 关闭服务器
     */
    void stop();

    /**
     * 服务器类型
     */
    enum Type {
        /**
         * HTTP 服务器
         */
        HTTP,
        /**
         * FTP 服务器
         */
        FTP
    }
}

package example.spring.boot.annotation.enable.service;

public interface CalculatingService {

    /**
     * 累加求和
     *
     * @param values
     * @return
     */
    Integer sum(Integer... values);
}

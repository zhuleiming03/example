package example.spring.service.factory;

import example.spring.service.Traffic;
import example.spring.service.impl.Car;
import example.spring.service.impl.Metro;

public class TrafficFactory {

    public static Traffic getStaticTraffic() {
        return new Car();
    }

    public Traffic getTraffic() {
        return new Metro();
    }

}

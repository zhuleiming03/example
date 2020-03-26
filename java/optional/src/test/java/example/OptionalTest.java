package example;

import example.domain.Car;
import example.domain.Person;
import example.service.PersonService;
import org.junit.Test;

public class OptionalTest {

    @Test
    public void personTest() {

        Person person = new Person();
        System.out.println("car not exist, default wheels: " + PersonService.wheels(person));

        person.setCar(null);
        System.out.println("set a null car, wheels: " + PersonService.wheels(person));

        Car car = new Car(4);
        person.setCar(car);
        System.out.println("set a new car, wheels: " + PersonService.wheels(person));
    }
}

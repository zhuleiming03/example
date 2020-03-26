package example.domain;

import example.domain.Car;

import java.util.Optional;

public class Person {

    public Person() {
        car = Optional.empty();
    }

    private Optional<Car> car;

    public Car getCar() {
        return car.orElseGet(() -> new Car(0));
    }

    public void setCar(Car car) {
        this.car = Optional.ofNullable(car);
    }
}

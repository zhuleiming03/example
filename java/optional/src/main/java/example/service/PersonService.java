package example.service;

import example.domain.Person;

public class PersonService {

    public static int wheels(Person person) {
        return person.getCar().getWheels();
    }
}

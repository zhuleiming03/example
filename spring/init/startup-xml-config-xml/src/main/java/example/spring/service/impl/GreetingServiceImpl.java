package example.spring.service.impl;

import example.spring.service.GreetingService;

public class GreetingServiceImpl implements GreetingService {
    @Override
    public String getGreeting(String name) {
        return "Hello " + name;
    }
}

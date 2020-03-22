package example.service;

import example.domain.Apple;

public interface ApplePredicate {

    boolean filter(Apple apple);
}

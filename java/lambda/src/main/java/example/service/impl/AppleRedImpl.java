package example.service.impl;

import example.domain.Apple;
import example.service.ApplePredicate;

public class AppleRedImpl implements ApplePredicate {

    @Override
    public boolean filter(Apple apple) {
        if ("red".equals(apple.getColor())) {
            return true;
        }
        return false;
    }
}

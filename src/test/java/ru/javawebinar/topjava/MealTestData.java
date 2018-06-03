package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.util.Objects;

public class MealTestData {

    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>(
            ((expected, actual) -> expected == actual ||
//                    (Objects.equals(expected, actual)) ||
                    ((Objects.equals(expected.getCalories(), actual.getCalories()) &&
                    Objects.equals(expected.getDateTime(), actual.getDateTime()) &&
                    Objects.equals(expected.getDescription(), actual.getDescription()))))
    );

}

package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DbPopulator;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;

import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {

    static {
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService mealService;

    @Autowired
    private DbPopulator dbPopulator;

    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }

    @Test
    public void get() {
        Meal meal = mealService.get(ADMIN_MEAL_ID, ADMIN_ID);
        MATCHER.assertEquals(ADMIN_MEAL, meal);
    }

    @Test(expected = NotFoundException.class)
    public void getOthersMeal(){
        Meal meal = mealService.get(ADMIN_MEAL_ID, USER_ID);
    }

    @Test
    public void delete() {
        mealService.delete(USER_MEAL_ID_1, USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL_2), mealService.getAll(USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void deleteOthers(){
        mealService.delete(USER_MEAL_ID_1, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> meals = mealService.getBetweenDates(LocalDate.of(2017,12,31),
                LocalDate.of(2018,1,2),
                USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL_1), meals);
    }

    @Test
    public void getBetweenDateTimes() {
        List<Meal> meals = mealService.getBetweenDateTimes(LocalDateTime.of(2018,1,1,10,0),
                LocalDateTime.of(2018,1,1,14,0),
                USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(USER_MEAL_1), meals);
    }

    @Test
    public void getAll() {
        List<Meal> meals = mealService.getAll(USER_ID);
        List<Meal> expectedMeals = new ArrayList<>();
        expectedMeals.add(USER_MEAL_1);
        expectedMeals.add(USER_MEAL_2);

        MATCHER.assertCollectionEquals(expectedMeals,meals);

    }

    @Test
    public void update() {
        Meal meal = new Meal(USER_MEAL_1);
        meal.setCalories(100);
        meal.setDescription("updated");
        mealService.update(meal, USER_ID);

        MATCHER.assertEquals(meal, mealService.get(USER_MEAL_ID_1, USER_ID));
    }

    @Test(expected = NotFoundException.class)
    public void updateOthers(){
        Meal meal = new Meal(USER_MEAL_1);
        meal.setCalories(100);
        meal.setDescription("updated");

        Meal actualMeal = mealService.update(meal, ADMIN_ID);

        MATCHER.assertEquals(meal, actualMeal);
    }

    @Test
    public void save() {
        Meal meal = new Meal();
        mealService.save(meal, USER_ID);
        List<Meal> expectedList = Arrays.asList(USER_MEAL_1, USER_MEAL_2, meal);

        MATCHER.assertCollectionEquals(expectedList, mealService.getAll(USER_ID));
    }
}
package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.Collection;

@Controller
public class MealRestController {

    @Autowired
    private MealService service;

    @Autowired
    private UserService userService;

    public Meal save(Meal meal){
//        MealWithExceed mealWithExceed = get(meal.getId(), meal.getUserId());
        service.save(meal);
        return meal;
    }

    public void delete(int id, int userId){
        service.delete(id,userId);
    }

    public Meal get(int id, int userId){
//        User user = userService.get(userId);
//        Meal meal = service.get(id, userId);
//        int calsThisDay = service.getAll(userId)
//                .stream()
//                .filter(m -> m.getDate() == meal.getDate())
//                .mapToInt(m -> m.getCalories())
//                .sum();
//        MealWithExceed mealWithExceed = MealsUtil.createWithExceed(meal, (calsThisDay+meal.getCalories()) > user.getCaloriesPerDay());
//        return mealWithExceed;
        return service.get(id, userId);
    }

    public Collection<MealWithExceed> getAll(int userId){
        User user = userService.get(userId);
        return MealsUtil.getFilteredWithExceeded(service.getAll(userId), LocalTime.MIN, LocalTime.MAX,user.getCaloriesPerDay());
    }

}
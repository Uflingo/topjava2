package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
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
        service.save(meal, AuthorizedUser.id());
        return meal;
    }

    public void delete(int id){
        service.delete(id,AuthorizedUser.id());
    }

    public Meal get(int id){
//        User user = userService.get(userId);
//        Meal meal = service.get(id, userId);
//        int calsThisDay = service.getAll(userId)
//                .stream()
//                .filter(m -> m.getDate() == meal.getDate())
//                .mapToInt(m -> m.getCalories())
//                .sum();
//        MealWithExceed mealWithExceed = MealsUtil.createWithExceed(meal, (calsThisDay+meal.getCalories()) > user.getCaloriesPerDay());
//        return mealWithExceed;
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<MealWithExceed> getAll(){
        User user = userService.get(AuthorizedUser.id());
        return MealsUtil.getFilteredWithExceeded(service.getAll(AuthorizedUser.id()), LocalTime.MIN, LocalTime.MAX,user.getCaloriesPerDay());
    }

}
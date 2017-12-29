package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.MEALS.forEach(m ->save(m, 1));
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
        }
        if (meal.getUserId() != 0) {
            if (meal.getUserId() == userId)
                repository.put(meal.getId(), meal);
            else
                return null;
        }
        else{
            meal.setUserId(userId);
            repository.put(meal.getId(), meal);
        }
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
        Meal m = repository.get(id);
        if (m.getUserId() == userId) {
            repository.remove(id);
            return true;
        }
        return false;

    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = repository.get(id);
        if (m.getUserId() == userId)
            return repository.get(id);
        return null;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream()
                .filter(m -> m.getUserId() == userId)
                .sorted((m1, m2) -> m2.getDateTime().compareTo(m1.getDateTime()))
                .collect(Collectors.toList());
    }
}


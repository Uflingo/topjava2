package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id, int userId) {
        if (!repository.delete(id,userId))
            throw new NotFoundException("Meal for this user not found");
    }

    @Override
    public Meal get(int id, int userId) {
        Meal m = repository.get(id, userId);
        if (m == null)
            throw new NotFoundException("Meal for this user not found");
        return m;

    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Collection<Meal> list = repository.getAll(userId);
        if(list.isEmpty())
            throw new NotFoundException("Meal for this user not found");
        return list;
    }
}
package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.Main;
import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

/**
 * Created by milu on 09.10.16.
 */
public interface MealRepository {
    Meal save(Meal meal);

    void delete (int id);

    Meal get(int id);

    Collection<Meal> getAll();


}

package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * GKislin
 * 15.09.2015.
 */
@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {

    public static final Comparator<Meal> MEAL_COMPARATOR = Comparator.comparing(Meal::getDateTime).reversed();

    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {

        MealsUtil.MEALS.forEach(um -> save(um, InMemoryUserRepositoryImpl.USER_ID));

//        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500), 1);
//        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000), 1);
//        save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500), 1);
//        save( new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000), 1);
//        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500), 1);
//        save(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510), 1);
//
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 1, 14, 0), "Admin Завтрак", 510), InMemoryUserRepositoryImpl.ADMIN_ID);
        save(new Meal(LocalDateTime.of(2015, Month.MAY, 1, 21, 0), "Admin Ужин", 1500), InMemoryUserRepositoryImpl.ADMIN_ID);

    }

    @Override
    public Meal save(Meal meal, int userId) {

        Objects.requireNonNull(meal);
        Integer mealId = meal.getId();
        if (meal.isNew()){
            mealId = counter.incrementAndGet();
            meal.setId(mealId);
        } else if(get(mealId, userId) == null) {
            return null;
        }

        Map<Integer, Meal> meals = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        meals.put(mealId, meal);
        return meal;
    }

    @Override
    public boolean delete(int id, int userId) {
         Map<Integer, Meal> meals = repository.get(userId);
        return meals != null && meals.remove(id) != null;
    }

    @Override
    public Meal get(int id, int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ? null : meals.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        Map<Integer, Meal> meals = repository.get(userId);
        return meals == null ?
                Collections.emptyList() :
                meals.values().stream().sorted(MEAL_COMPARATOR).collect(Collectors.toList());
    }

    @Override
    public Collection<Meal> getBetween (LocalDateTime startDateTime, LocalDateTime endDateTime, int userId){
            Objects.requireNonNull(startDateTime);
            Objects.requireNonNull(endDateTime);
        return getAll(userId).stream()
                .filter(um -> TimeUtil.isBetween(um.getDateTime(), startDateTime, endDateTime))
                .collect(Collectors.toList());
    }

}


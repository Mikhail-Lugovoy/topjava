package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> filteredWithExceeded = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        filteredWithExceeded.forEach(System.out::println);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
//        List<UserMealWithExceed> exceedList = new ArrayList<>();
//        int scoreCalories;
//        LocalDate day = null;
//        boolean exceed = false;
//        for (int i = 0; i < mealList.size(); i++) {
//            if (mealList.get(i).getDateTime().toLocalDate() != day) {
//                day = mealList.get(i).getDateTime().toLocalDate();
//                scoreCalories = 0;
//                for (UserMeal aMealList : mealList)
//                    if (aMealList.getDateTime().toLocalDate().equals(day)) {
//                        scoreCalories += aMealList.getCalories();
//                    }
//                if (scoreCalories > caloriesPerDay)
//                    exceed = true;
//            }
//            if (mealList.get(i).getDateTime().getHour() > startTime.getHour() && mealList.get(i).getDateTime().getHour() < endTime.getHour()){
//                exceedList.add(new UserMealWithExceed(mealList.get(i).getDateTime(), mealList.get(i).getDescription(), mealList.get(i).getCalories(), exceed));
//            }
//
//
//        }

//        Map<LocalDate, Integer> caloriesSumPerDate = new HashMap<>();
//        for (UserMeal meal : mealList) {
//            LocalDate dateTime = meal.getDateTime().toLocalDate();
//            caloriesSumPerDate.put(dateTime, caloriesSumPerDate.getOrDefault(dateTime, 0) + meal.getCalories());
//        }
//
//        List<UserMealWithExceed> mealExceed = new ArrayList<>();
//        for (UserMeal meal : mealList){
//            LocalDateTime datetime = meal.getDateTime();
//            if(TimeUtil.isBetween(datetime.toLocalTime(), startTime, endTime)){
//                mealExceed.add(new UserMealWithExceed(datetime, meal.getDescription(), meal.getCalories(),
//                        (caloriesSumPerDate.get(datetime.toLocalDate())) > caloriesPerDay));
//            }
//
//        }
        Map<LocalDate, Integer> caloriesSumByDate = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(),
                Collectors.summingInt(UserMeal::getCalories)));
         return mealList.stream().filter(um -> TimeUtil.isBetween(um.getDateTime().toLocalTime(), startTime, endTime))
                .map(um -> new UserMealWithExceed(um.getDateTime(), um.getDescription(), um.getCalories(),
                        caloriesSumByDate.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }
}

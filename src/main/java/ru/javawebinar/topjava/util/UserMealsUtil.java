package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExceed> exceedList = new ArrayList<>();
        int scoreCalories;
        LocalDate day = null;
        boolean exceed = false;
        for (int i = 0; i < mealList.size(); i++) {
            if (mealList.get(i).getDateTime().toLocalDate() != day) {
                day = mealList.get(i).getDateTime().toLocalDate();
                scoreCalories = 0;
                for (int j = 0; j < mealList.size(); j++){
                    if (mealList.get(j).getDateTime().toLocalDate().equals(day)){
                        scoreCalories += mealList.get(j).getCalories();
                    }
                }
                if (scoreCalories > caloriesPerDay)
                    exceed = true;
            }
            if (mealList.get(i).getDateTime().getHour() > startTime.getHour() && mealList.get(i).getDateTime().getHour() < endTime.getHour()){
                exceedList.add(new UserMealWithExceed(mealList.get(i).getDateTime(), mealList.get(i).getDescription(), mealList.get(i).getCalories(), exceed));
            }


        }

        //fot test
        for (int i = 0; i < exceedList.size(); i++) {
            System.out.println(exceedList.get(i).getDateTime() + " " + exceedList.get(i).getDescription() + " " + exceedList.get(i).getCalories() + " " + exceedList.get(i).isExceed());
        }

        return exceedList;
    }
}

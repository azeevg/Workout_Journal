package com.spbstu.appmath.Workout_Journal;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlannedTrains {
    public static List<Training> getAll() {
        // TODO: 07.06.2016 DataBase вытаскивать элементы из БД, а не создавать их руками

        final List<Training> plannedTrains = new CopyOnWriteArrayList<>();

        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i);
            plannedTrains.add(training);
        }
        return plannedTrains;
    }

    /**
     *
     * @param training
     * @return true if deleting is successful and false otherwise
     */
    public static boolean deleteInstance(final Training training) {
        // TODO: 07.06.2016 DataBase удалить запись из таблицы
        return false;
    }
}

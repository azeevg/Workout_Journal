package com.spbstu.appmath.Workout_Journal;

import java.util.concurrent.CopyOnWriteArrayList;

public class PlannedTrains {
    public static CopyOnWriteArrayList<Training> getAll() {
        final CopyOnWriteArrayList<Training> plannedTrains = new CopyOnWriteArrayList<>();

        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i);
            plannedTrains.add(training);
//            System.out.println(training);
        }
        return plannedTrains;
    }
}

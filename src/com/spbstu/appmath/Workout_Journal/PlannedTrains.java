package com.spbstu.appmath.Workout_Journal;

import java.util.ArrayList;

public class PlannedTrains {
    public static ArrayList<Training> getAll() {
        final ArrayList<Training> plannedTrains = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i);
            plannedTrains.add(training);
//            System.out.println(training);
        }
        return plannedTrains;
    }
}

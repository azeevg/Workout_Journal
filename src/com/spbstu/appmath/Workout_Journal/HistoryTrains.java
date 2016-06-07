package com.spbstu.appmath.Workout_Journal;

import java.util.ArrayList;

public class HistoryTrains {
    public ArrayList<Training> getAll() {
        final ArrayList<Training> historyTrains = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i, "1" + i + ".04.2016");
            historyTrains.add(training);
        }
        return historyTrains;
    }
}

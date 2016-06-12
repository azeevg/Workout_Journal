package com.spbstu.appmath.Workout_Journal;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlannedTrains {
    public static List<Training> getAll(DBHelper dbHelper) {
        final List<Training> plannedTrains = new CopyOnWriteArrayList<>();

        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i);
            plannedTrains.add(training);
        }
        return plannedTrains;
    }


    public static boolean deleteInstance(final Training training) {
        return false;
    }
}

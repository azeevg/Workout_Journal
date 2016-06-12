package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "workout_journal.dblite";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public List<Training> getAllPlannedTrainings() {
        final List<Training> plannedTrains = new CopyOnWriteArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + DBContract.WorkoutsPlan.TABLE, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            String workoutName = res.getString(res.getColumnIndex(DBContract.WorkoutsPlan.COLUMN_NAME));
            plannedTrains.add(new Training(workoutName));
            res.moveToNext();
        }
        return plannedTrains;
    }

    public boolean deletePlannedTraining(final Training training) {
        // TODO: 07.06.2016 DataBase удалить запись из таблицы
        return false;
    }
}

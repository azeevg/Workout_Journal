package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;



public class DBHelper extends SQLiteOpenHelper {

    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "workout_journal.dblite";
    private String DB_PATH = null;

    public DBHelper(Context context) {
        super(context, context.getFilesDir().getPath()+DB_NAME, null, DB_VERSION);
        DB_PATH = context.getFilesDir().getPath();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {}

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public List<Training> getAllPlannedTrainings() {

        final List<Training> plannedTrains = new CopyOnWriteArrayList<>();
        /*SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM '" + DBContract.WorkoutsPlan.TABLE + "'", null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            String workoutName = res.getString(res.getColumnIndex(DBContract.WorkoutsPlan.COLUMN_NAME));
            plannedTrains.add(new Training(workoutName));
            res.moveToNext();
        }
        res.close();*/

        // mock
        for (int i = 1; i < 10; i++) {
            Training training = new Training("Название тренировки " + i);
            plannedTrains.add(training);
        }
        //

        return plannedTrains;
    }

    public boolean deletePlannedTraining(final Training training) {
        /*SQLiteDatabase db = this.getWritableDatabase();
        if (training.date == null)
            db.execSQL("DELETE FROM " + DBContract.WorkoutsPlan.TABLE + " WHERE " +
                    DBContract.WorkoutsPlan.COLUMN_NAME + " = " + training.name);
        else
            db.execSQL("DELETE FROM " + DBContract.WorkoutsDone.TABLE + " WHERE " +
                    DBContract.WorkoutsDone.COLUMN_NAME + " = '" + training.name + "' AND " +
                    DBContract.WorkoutsDone.COLUMN_DATE + " = '" + training.date + "'");
        return false;*/
        return true;
    }
}

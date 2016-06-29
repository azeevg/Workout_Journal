package com.spbstu.appmath.Workout_Journal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class DBHelper {

    public static final String DB_NAME = "workout_journal.db";
    private String DB_PATH_NAME = null;
    private final Context context;

    public DBHelper(final Context _context) {
        this.context = _context;
        String DB_PATH = context.getFilesDir().getPath() + "/";
        DB_PATH_NAME = DB_PATH + DB_NAME;
    }

    public void createDataBase() throws IOException {
        boolean dbExist = checkDataBase();
        if (!dbExist) {
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase checkDB = null;
        try{
            checkDB = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            //database does't exist yet.
        }
        if(checkDB != null){
            checkDB.close();
        }
        return checkDB != null;
        //return false;
    }

    private void copyDataBase() throws IOException {
        InputStream input = context.getAssets().open(DB_NAME);
        OutputStream output = new FileOutputStream(DB_PATH_NAME);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = input.read(buffer)) > 0){
            output.write(buffer, 0, length);
        }

        output.flush();
        output.close();
        input.close();
    }

    public List<Training> getAllPlannedTrainings() {
        final List<Training> trainings = new CopyOnWriteArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.rawQuery("SELECT * FROM " + DBContract.WorkoutsPlan.TABLE, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            String name = res.getString(res.getColumnIndex(DBContract.WorkoutsPlan.COLUMN_NAME));
            trainings.add(new Training(name));
            res.moveToNext();
        }
        res.close();
        return trainings;
    }

    public List<Training> getAllDoneTrainings() {
        final List<Training> trainings = new CopyOnWriteArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.rawQuery("SELECT * FROM " + DBContract.WorkoutsDone.TABLE, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            String name = res.getString(res.getColumnIndex(DBContract.WorkoutsDone.COLUMN_NAME));
            String date = res.getString(res.getColumnIndex(DBContract.WorkoutsDone.COLUMN_DATE));
            trainings.add(new Training(name, date));
            res.moveToNext();
        }
        res.close();
        return trainings;
    }

    public boolean deletePlannedTraining(final Training training) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        if (training.date == null) {
            db.delete(DBContract.WorkoutsPlan.TABLE,
                    DBContract.WorkoutsPlan.COLUMN_NAME + " = '" + training.name + "'", null);
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public boolean deleteDoneTraining(final Training training) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READWRITE);
        if (training.date != null) {
            db.delete(DBContract.WorkoutsDone.TABLE,
                    DBContract.WorkoutsDone.COLUMN_NAME + " = '" + training.name + "' AND " +
                    DBContract.WorkoutsDone.COLUMN_DATE + " = '" + training.date + "'", null);
            db.close();
            return true;
        }
        db.close();
        return false;
    }

    public List<Exercise> getExercises() {
        List<Exercise> exercises = new ArrayList<>();
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.query(DBContract.Exercises.TABLE,
                new String[]{DBContract.Exercises.COLUMN_NAME},
                null, null, null, null, null);
        res.moveToFirst();
        while(!res.isAfterLast()) {
            String name = res.getString(res.getColumnIndex(DBContract.Exercises.COLUMN_NAME));
            exercises.add(new Exercise(name, false));
            res.moveToNext();
        }
        res.close();
        return exercises;
    }

    public String getExerciseInfo(Exercise exercise) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READONLY);
        Cursor res = db.query(DBContract.Exercises.TABLE,
                new String[]{DBContract.Exercises.COLUMN_DESCRIPTION},
                DBContract.Exercises.COLUMN_NAME + "='" + exercise.getName() + "'",
                null, null, null, null);
        res.moveToFirst();
        String description = res.getString(res.getColumnIndex(DBContract.Exercises.COLUMN_DESCRIPTION));
        res.close();
        return description;
    }


    public void writePlannedTrainingAndSets(Training training, List<List<Set>> sets) {
        SQLiteDatabase db = SQLiteDatabase.openDatabase(DB_PATH_NAME, null, SQLiteDatabase.OPEN_READWRITE);

        // training
        ContentValues newTraining = new ContentValues();
        newTraining.put(DBContract.WorkoutsPlan.COLUMN_NAME, training.getName());
        int workoutId = (int)db.insert(DBContract.WorkoutsPlan.TABLE, null, newTraining);

        // sets
        for (List<Set> listSet : sets) {
            String exerciseName = listSet.get(0).getExercise().getName();
            Cursor res = db.query(DBContract.Exercises.TABLE,
                    new String[]{DBContract.Exercises.COLUMN_ID},
                    DBContract.Exercises.COLUMN_NAME + "='" + exerciseName + "'",
                    null, null, null, null);
            res.moveToFirst();
            String exerciseId = res.getString(res.getColumnIndex(DBContract.Exercises.COLUMN_ID));
            res.close();
            for (Set set : listSet) {
                ContentValues newSet = new ContentValues();
                newSet.put(DBContract.SetsPlan.COLUMN_EXERCISE_ID, exerciseId);
                newSet.put(DBContract.SetsPlan.COLUMN_WORKOUT_ID, workoutId);
                newSet.put(DBContract.SetsPlan.COLUMN_WEIGHT, set.getWeight());
                newSet.put(DBContract.SetsPlan.COLUMN_REPS, set.getTimes());
                db.insert(DBContract.SetsPlan.TABLE, null, newSet);
            }
        }
    }
}

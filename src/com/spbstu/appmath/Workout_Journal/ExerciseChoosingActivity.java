package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class ExerciseChoosingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_full_list);

        final List<Exercise> exerciseList = displayExercises();



    }

    private List<Exercise> displayExercises() {
        // TODO: 21.06.2016 DATABASE: вернуть все упражнения из БД
//        final List<Exercise> exercises = db.blabla();
        final List<Exercise> exercises = new ArrayList<>();

        return exercises;
    }
}

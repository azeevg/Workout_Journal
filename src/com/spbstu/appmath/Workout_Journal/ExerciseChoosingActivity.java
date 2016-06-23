package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class ExerciseChoosingActivity extends Activity {

    private Exercise exercise = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_full_list);

        final List<Exercise> exerciseList = displayExercises();

    }

    private List<Exercise> displayExercises() {
        // TODO: 21.06.2016 DATABASE: вернуть все упражнения из БД
//        final List<Exercise> exercises = db.blabla();
        final List<Exercise> exercises = mockGetExercises();
        ListView listView = (ListView) findViewById(R.id.trainList);

        System.out.println(exercises);
        listView.setAdapter(new ExerciseListAdapter(this, exercises, listView));
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicked on exercise: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        return exercises;
    }


    private List<Exercise> mockGetExercises() {
        final List<Exercise> list = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            list.add(new Exercise("name " + i, "description " + i, false));
        }
        return list;
    }
}

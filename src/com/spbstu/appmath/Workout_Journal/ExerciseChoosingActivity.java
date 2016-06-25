package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
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
        final DBHelper dbHelper = new DBHelper(this);
        final List<Exercise> exercises = dbHelper.getExercises();
        final ListView listView = (ListView) findViewById(R.id.trainList);

        //System.out.println(exercises);
        listView.setAdapter(new ExerciseListAdapter(this, exercises, listView));
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExerciseChoosingActivity.this, ExerciseInfoActivity.class);
                Exercise exercise = (Exercise) listView.getAdapter().getItem(position);
                //String description = dbHelper.getExerciseInfo(exercise);
                String description = exercise.getDescription();
                String name = exercise.getName();
                intent.putExtra("name", name);
                intent.putExtra("description", description);
                startActivity(intent);
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

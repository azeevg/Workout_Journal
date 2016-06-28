package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ExerciseChoosingActivity extends Activity {

    private String chosenExerciseName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercises_full_list);
        displayExercises();
    }

    private void displayExercises() {
        final DBHelper dbHelper = new DBHelper(this);
        final List<Exercise> exercises = dbHelper.getExercises();
        final ListView listView = (ListView) findViewById(R.id.trainList);

        listView.setAdapter(new ExerciseListAdapter(this, exercises, listView));
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ExerciseChoosingActivity.this, ExerciseInfoActivity.class);
                Exercise exercise = (Exercise) listView.getAdapter().getItem(position);
                String description = dbHelper.getExerciseInfo(exercise);
                String name = exercise.getName();
                intent.putExtra("name", name);
                intent.putExtra("description", description);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent();
        intent.putExtra("exerciseSets", data.getExtras().getSerializable("exerciseSets"));
        setResult(RESULT_OK, intent);
        finish();
    }

}

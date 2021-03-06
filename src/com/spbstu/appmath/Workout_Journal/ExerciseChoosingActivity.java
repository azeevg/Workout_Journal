package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class ExerciseChoosingActivity extends Activity {

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
                final Intent intent = new Intent(ExerciseChoosingActivity.this, SetsCreatingActivity.class);
                intent.putExtra("exercise", exercises.get(position));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent();
        if (resultCode == RESULT_OK) {
            intent.putExtra("exerciseSets", data.getExtras().getSerializable("exerciseSets"));
            setResult(RESULT_OK, intent);
        }
        else
            setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }
}

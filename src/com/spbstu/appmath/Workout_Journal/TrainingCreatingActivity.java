package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrainingCreatingActivity extends Activity {

    private List<List<Set>> exercises = new ArrayList<>();
    private TrainingListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_creating);

        displayExpandableList();

        final ImageButton addButton = (ImageButton) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(TrainingCreatingActivity.this,
                        ExerciseChoosingActivity.class);
                startActivityForResult(intent, 1);
            }
        });
    }


    private void displayExpandableList() {
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.workoutExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        adapter = new TrainingListAdapter(this, exercises, listView, true);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ArrayList<Set> sets = (ArrayList<Set>) data.getExtras().getSerializable("exerciseSets");
        exercises.add(sets);
        adapter.notifyDataSetChanged();
    }
}

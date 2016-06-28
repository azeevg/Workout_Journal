package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TrainingCreatingActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_creating);

        final List<List<Set>> reiterations = displayExpandableList();
        final ImageButton addButton = (ImageButton) findViewById(R.id.button_add);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseExercise(v);
            }
        });
    }


    private List<List<Set>> displayExpandableList() {
        final List<List<Set>> groups = new CopyOnWriteArrayList<>();
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.workoutExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        listView.setAdapter(new TrainingListAdapter(this, groups, listView, true));

        return groups;
    }

    private void chooseExercise(final View view) {
        final Intent intent = new Intent(this, ExerciseChoosingActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}

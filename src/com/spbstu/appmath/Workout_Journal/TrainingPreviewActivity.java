package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class TrainingPreviewActivity extends Activity {

    public static final String TRAINING = "training";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_preview);

        final Bundle extras = getIntent().getExtras();
        final int id = extras.getInt(MainActivity.TRAINING_ID);


        try {
            final List<List<Set>> reiterations = displayExpandableList(id);
            final ImageButton startButton = (ImageButton) findViewById(R.id.startButton);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(TrainingPreviewActivity.this, TrainingStartingActivity.class);
                    intent.putExtra(TRAINING, (Serializable) reiterations);
                    startActivity(intent);
                }
            });
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private List<List<Set>> displayExpandableList(final int id) throws InstantiationException, IllegalAccessException {
        // TODO: 25.06.2016 DATABASE: вытащить из БД все подходы
        final List<Set> setList = new ArrayList<>();
        setList.add(new Set(new Exercise("Упражнение 1", 1), 50.0, 4));
        setList.add(new Set(new Exercise("Упражнение 2", 2), 60.0, 3));
        setList.add(new Set(new Exercise("Упражнение 2", 2), 60.0, 4));
        setList.add(new Set(new Exercise("Упражнение 1", 1), 30.0, 4));
        setList.add(new Set(new Exercise("Упражнение 4", 4), 20.0, 4));
        setList.add(new Set(new Exercise("Упражнение 4", 4), 40.0, 2));

        final List<List<Set>> groups = mockGetReiterations(setList);

        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.previewExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new TrainingListAdapter(this, groups, listView, false));

        return groups;
    }

    private List<List<Set>> mockGetReiterations(final List<Set> sets) throws IllegalAccessException, InstantiationException {
        final List<List<Set>> groups = new CopyOnWriteArrayList<>();
        final List<Exercise> exerciseList = new LinkedList<>();

        for (Set set : sets) {
            int index = exerciseList.indexOf(set.getExercise());

            if (index == -1) {
                exerciseList.add(set.getExercise());
                groups.add(new CopyOnWriteArrayList<Set>());
                index = exerciseList.size() - 1;
            }

            groups.get(index).add(set);
        }

        return groups;
    }

    public static List<Exercise> getExerciseList(final List<List<Set>> groups) {
        final List<Exercise> exerciseList = new LinkedList<>();

        for (List<Set> group : groups) {
            for (Set set : group) {
                if (!exerciseList.contains(set.getExercise())) {
                    exerciseList.add(set.getExercise());
                    System.out.println(set.getExercise().getName());
                }
            }
        }
        return exerciseList;
    }
}

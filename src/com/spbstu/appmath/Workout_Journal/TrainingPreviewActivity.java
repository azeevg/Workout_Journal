package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageButton;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class TrainingPreviewActivity extends Activity {

    public static final String SETS_PLANNED = "setsPlanned";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_preview);

        final Bundle extras = getIntent().getExtras();
        final int id = extras.getInt(MainActivity.TRAINING_ID);

        try {
            final List<List<Set>> groupedSets = displayGroupedSets(id);
            final ImageButton startButton = (ImageButton) findViewById(R.id.startButton);

            startButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(TrainingPreviewActivity.this, ActiveTrainingActivity.class);
                    intent.putExtra(SETS_PLANNED, (Serializable) groupedSets);
                    startActivityForResult(intent, 1);
                }
            });
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<List<Set>> groupedSetsDone =
                (List<List<Set>>) data.getSerializableExtra(ActiveTrainingActivity.SETS_DONE);

        String trainingName = getIntent().getStringExtra(MainActivity.TRAINING_NAME);

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        String date = df.format(c.getTime());

        DBHelper db = new DBHelper(this);
        db.writeDoneTrainingAndSets(trainingName, date, groupedSetsDone);

        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    private List<List<Set>> displayGroupedSets(final int id) throws InstantiationException, IllegalAccessException {
        DBHelper db = new DBHelper(this);
        final List<Set> sets = db.getPlannedSets(id);
        final List<List<Set>> groupedSets = getGroupedSets(sets);

        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.previewExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new TrainingListAdapter(this, groupedSets, listView, false));

        return groupedSets;
    }

    private List<List<Set>> getGroupedSets(final List<Set> sets) throws IllegalAccessException, InstantiationException {
        final List<List<Set>> groupedSets = new CopyOnWriteArrayList<>();
        final List<Exercise> exerciseList = new LinkedList<>();

        for (Set set : sets) {
            int index = exerciseList.indexOf(set.getExercise());
            if (index == -1) {
                exerciseList.add(set.getExercise());
                groupedSets.add(new CopyOnWriteArrayList<Set>());
                index = exerciseList.size() - 1;
            }
            groupedSets.get(index).add(set);
        }

        return groupedSets;
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

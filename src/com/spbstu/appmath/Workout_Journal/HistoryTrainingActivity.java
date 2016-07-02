package com.spbstu.appmath.Workout_Journal;


import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Activity with list of done sets grouped in sublists by their exercises
 */
public class HistoryTrainingActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_training);

        int trainingId = getIntent().getIntExtra(MainActivity.TRAINING_ID, 0);
        DBHelper db = new DBHelper(this);
        final List<Set> sets = db.getDoneSets(trainingId);
        final List<List<Set>> groupedSets = getGroupedSets(sets);

        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.historyExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new TrainingListAdapter(this, groupedSets, listView, false));
    }

    private List<List<Set>> getGroupedSets(final List<Set> sets) {
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
}

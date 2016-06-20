package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TrainingCreatingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_creating);

        final ImageButton addButton;
    }


    private List<List<Reiteration>> displayExpandableList() {
        final List<List<Reiteration>> groups = new ArrayList<>();
        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.workoutExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);

        listView.setAdapter(new TrainingListAdapter(this, groups, listView));

        return groups;
    }
}

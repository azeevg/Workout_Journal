package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


/**
 * Activity with list of done trainings
 */
public class HistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        displayHistoryTrainings();
    }

    private void displayHistoryTrainings() {
        DBHelper db = new DBHelper(this);
        final List<Workout> historyTrains = db.getAllDoneWorkouts();
        ListView listView = (ListView) findViewById(R.id.historyListView);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new HistoryListAdapter(this, R.layout.history_list_item, historyTrains, listView, db));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(HistoryActivity.this, HistoryWorkoutActivity.class);
                intent.putExtra(MainActivity.TRAINING_ID, historyTrains.get(position).getId());
                startActivity(intent);
            }
        });
    }
}

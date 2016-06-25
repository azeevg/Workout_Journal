package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        displayHistoryTrainings();
    }

    private void displayHistoryTrainings() {
        DBHelper db = new DBHelper(this);
        final List<Training> historyTrains = db.getAllDoneTrainings();
        ListView listView = (ListView) findViewById(R.id.historyListView);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new HistoryListAdapter(this, R.layout.history_list_item, historyTrains, listView, db));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 19.06.2016 HistoryTrainingActivity

                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });
        //return historyTrains;
    }
}

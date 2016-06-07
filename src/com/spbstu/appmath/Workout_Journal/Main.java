package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;


public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        final List<Training> trainList = displayListView();

    }

    private List<Training> displayListView() {
        final List<Training> plannedTrains = PlannedTrains.getAll();
        ListView listView = (ListView) findViewById(R.id.trainList);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new MainListAdapter(this, R.layout.list_item, plannedTrains, listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return plannedTrains;
    }
}

package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;


public class Main extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        // create db in app's folder if not exist
        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<Training> trainList = displayListView();
    }

    private List<Training> displayListView() {
        DBHelper db = new DBHelper(this);
        final List<Training> plannedTrains = db.getAllPlannedTrainings();
        ListView listView = (ListView) findViewById(R.id.trainList);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new MainListAdapter(this, R.layout.list_item, plannedTrains, listView, db));
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

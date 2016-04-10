package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main extends Activity {

    public static final String NAME = "name";
    public static final String DATE = "date";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_removing);
        ListView listView = (ListView) findViewById(R.id.historyListView);
        final ArrayList<HashMap<String, String>> historyItems = new ArrayList<>();
        HashMap<String, String> hm;

        for (int i = 1; i < 10; i++) {
            hm = new HashMap<>();
            hm.put(NAME, "Название тренировки " + i);
            hm.put(DATE, "1" + i + ".04.2016");
            historyItems.add(hm);
            System.out.println(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, historyItems,
                R.layout.history_list_item, new String[]{NAME, DATE},
                new int[]{R.id.historyItemName, R.id.historyItemDate});

        listView.setAdapter(adapter);

//        String[] trainings = getResources().getStringArray(R.array.exercises);

        /*String[] trainings = getResources().getStringArray(R.array.trainings);
        HashMap<String, String> hm = null;
        ArrayList<Map<String, String>> workouts = new ArrayList<>();
        for (String workout : trainings) {
            hm = new HashMap<>();
            hm.put(NAME, workout);
            workouts.add(hm);
        }
        setContentView(R.layout.main_removing);
        ListView listView = (ListView) findViewById(R.id.mainTrainList);
        SimpleAdapter adapter = new SimpleAdapter(this, workouts, R.layout.list_item,
                new String[]{NAME}, new int[]{R.id.listItem});
        listView.setAdapter(adapter);*/

    }
}

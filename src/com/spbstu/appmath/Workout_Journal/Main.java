package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main extends Activity {

    public static final String NAME = "name";
    public static final String DATE = "date";
    private ArrayList<HashMap<String, Object>> historyItems;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
//      String[] trainings = getResources().getStringArray(R.array.trainings);
//        String[] trainings = getResources().getStringArray(R.array.exercises);
        ListView listView = (ListView) findViewById(R.id.historyListView);

        historyItems = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hm;

        for (int i = 1; i < 10; i++) {
            hm = new HashMap<>();
            hm.put(NAME, "Название тренировки " + i);
            hm.put(DATE, "1" + i + ".04.2016");
            historyItems.add(hm);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, historyItems,
                R.layout.history_list_item, new String[]{"NAME", "DATE"},
                new int[]{R.id.historyItemName, R.id.historyItemDate});

        listView.setAdapter(adapter);


//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.history_list_item, trainings);
//        listView.setAdapter(adapter);
       /* listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CheckedTextView textView = (CheckedTextView) view.findViewById(view.getId());
                textView.setChecked(!textView.isChecked());
            }
        });*/
//        setContentView(R.layout.not_main);
    }
}

package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Switch;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        String[] trainings = getResources().getStringArray(R.array.trainings);
        ListView listView = (ListView) findViewById(R.id.trainList);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,	R.layout.list_item, trainings);
        listView.setAdapter(adapter);
//        setContentView(R.layout.not_main);
    }
}

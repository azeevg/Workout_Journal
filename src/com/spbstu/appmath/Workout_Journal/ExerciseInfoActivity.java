package com.spbstu.appmath.Workout_Journal;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class ExerciseInfoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exercise_info);

        Intent intent = getIntent();

        final TextView titleText = (TextView) findViewById(R.id.exerciseTitle);
        titleText.setText(intent.getStringExtra("name"));

        final TextView descriptionText = (TextView) findViewById(R.id.descriptionText);
        descriptionText.setText(intent.getStringExtra("description"));
    }

}

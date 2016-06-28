package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;

import java.util.List;

public class TrainingStartingActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);

        System.out.println("final List<List<Set>> list ");
        final List<List<Set>> list = (List<List<Set>>) getIntent().getExtras().getSerializable(TrainingPreviewActivity.TRAINING);

        for (List<Set> sets : list) {
            for (Set set : sets) {
                System.out.println(set);
            }
        }
    }
}

package com.spbstu.appmath.Workout_Journal;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SetsCreatingActivity extends Activity {

    private ArrayList<Set> sets = new ArrayList<Set>();
    ArrayAdapter<Set> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sets_creating);

        ListView listView = (ListView) findViewById(R.id.setsListView);
        adapter = new SetsListAdapter(this, R.layout.set_list_item, sets, listView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        final Exercise exercise = (Exercise)intent.getExtras().getSerializable("exercise");

        final ImageButton buttonAdd = (ImageButton) findViewById(R.id.button_add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(SetsCreatingActivity.this);
                final LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_set_weight, null);

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                final EditText editReps = (EditText) dialogView.findViewById(R.id.editReps);
                                final EditText editWeight = (EditText) dialogView.findViewById(R.id.editWeight);
                                sets.add(new Set(exercise, Double.parseDouble(editWeight.getText().toString()),
                                        Integer.parseInt(editReps.getText().toString())));
                                adapter.notifyDataSetChanged();
                                ImageButton buttonEnd = (ImageButton) findViewById(R.id.button_end);
                                if (buttonEnd.getVisibility() == View.INVISIBLE)
                                    buttonEnd.setVisibility(View.VISIBLE);
                            }
                        });
                builder.setCancelable(true);
                final AlertDialog dialog = builder.create();
                dialog.setView(dialogView, 0, 0, 0, 0);
                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        final View v = (View) dialog.getButton(DialogInterface.BUTTON_NEGATIVE).getParent();
                        v.setBackgroundColor(getResources().getColor(R.color.light_gray_1));
                    }
                });
                dialog.show();
            }
        });

        final ImageButton buttonEnd = (ImageButton) findViewById(R.id.button_end);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (sets.size() > 0) {
                    Intent intent = new Intent();
                    intent.putExtra("exerciseSets", (Serializable) sets);
                    setResult(RESULT_OK, intent);
                }
                else
                    setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

}

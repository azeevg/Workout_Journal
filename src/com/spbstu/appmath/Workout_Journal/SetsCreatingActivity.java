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

import java.util.ArrayList;
import java.util.List;

public class SetsCreatingActivity extends Activity {

    private ArrayList<Set> sets = new ArrayList<Set>();
    ArrayAdapter<Set> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sets_creating);

        Intent intent = getIntent();
        ListView listView = (ListView) findViewById(R.id.setsListView);
        adapter = new SetsListAdapter(this, R.layout.set_list_item, sets, listView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(adapter);
        //displaySets();

        final ImageButton addButton = (ImageButton) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
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
                                sets.add(new Set(Integer.parseInt(editReps.getText().toString()),
                                        Integer.parseInt(editWeight.getText().toString())));
                                adapter.notifyDataSetChanged();
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
    }

    /*@Override
    protected void onResume() {
        displaySets();
    }*/

    private void displaySets() {
        ListView listView = (ListView) findViewById(R.id.setsListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new SetsListAdapter(this, R.layout.set_list_item, sets, listView));
    }

}

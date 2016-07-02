package com.spbstu.appmath.Workout_Journal;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;

public class SetsCreatingActivity extends Activity {

    private ArrayList<Set> sets = new ArrayList<>();
    ArrayAdapter<Set> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sets_creating);

        ListView listView = (ListView) findViewById(R.id.setsListView);
        adapter = new SetsListAdapter(this, R.layout.sets_creating_list_item, sets, listView);
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

                                double weight = 0;
                                if (!editWeight.getText().toString().equals(""))
                                    weight = Double.parseDouble(editWeight.getText().toString());
                                int times = Integer.parseInt(editReps.getText().toString());

                                sets.add(new Set(exercise, weight, times));
                                adapter.notifyDataSetChanged();
                                ImageButton buttonEnd = (ImageButton) findViewById(R.id.button_end);
                                if (buttonEnd.getVisibility() == View.INVISIBLE)
                                    buttonEnd.setVisibility(View.VISIBLE);

                                Toast.makeText(getApplicationContext(), "Подход добавлен", Toast.LENGTH_SHORT).show();
                            }
                        });
                builder.setCancelable(true);

                final AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                dialog.setView(dialogView, 0, 0, 0, 0);

                EditText editTimes = (EditText) dialogView.findViewById(R.id.editReps);
                editTimes.setFilters(new InputFilter[]{ new SetTimesFilter() });
                EditText editWeight = (EditText) dialogView.findViewById(R.id.editWeight);
                editWeight.setFilters(new InputFilter[]{ new SetWeightFilter() });

                editTimes.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (TextUtils.isEmpty(s)) {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                        } else {
                            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                        }
                    }
                });

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        final View v = (View) dialog.getButton(DialogInterface.BUTTON_NEGATIVE).getParent();
                        v.setBackgroundColor(getResources().getColor(R.color.light_gray_1));
                        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                    }
                });
                dialog.show();
            }
        });

        final ImageButton buttonEnd = (ImageButton) findViewById(R.id.button_end);
        buttonEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                if (sets.size() > 0) {
                    intent.putExtra("exerciseSets", sets);
                    setResult(RESULT_OK, intent);
                }
                else {
                    setResult(RESULT_CANCELED, intent);
                }
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
        //moveTaskToBack(true);
    }
}

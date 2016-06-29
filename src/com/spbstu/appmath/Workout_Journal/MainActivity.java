package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    public static final String TRAINING_NAME = "trainingName";
    public static final String TRAINING_ID = "training_id";

    DBHelper db;
    List<Training> plannedTrains;
    MainListAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Activity activity = this;

        setContentView(R.layout.main);

        // create db in app's folder if not exist
        db = new DBHelper(this);
        try {
            db.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        displayTrainings();

        final ImageButton addButton = (ImageButton) findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                final LayoutInflater inflater = getLayoutInflater();
                final View dialogView = inflater.inflate(R.layout.dialog_workout_name, null);

                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                createNewTraining(dialogView);
                            }
                        });
                builder.setCancelable(true);
                final AlertDialog dialog = builder.create();
                dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
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

        final ImageButton historyButton = (ImageButton) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });

        final ImageButton helpButton = (ImageButton) findViewById(R.id.helpButton);
        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void displayTrainings() {
        plannedTrains = db.getAllPlannedTrainings();
        ListView listView = (ListView) findViewById(R.id.trainList);
        adapter = new MainListAdapter(this, R.layout.list_item, plannedTrains, listView, db);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(getApplicationContext(), TrainingPreviewActivity.class);
                intent.putExtra(TRAINING_ID, plannedTrains.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private void createNewTraining(final View view) {
        final Intent intent = new Intent(this, TrainingCreatingActivity.class);
        final EditText editText = (EditText) view.findViewById(R.id.editName);
        intent.putExtra(TRAINING_NAME, editText.getText().toString());
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Training training = (Training)data.getExtras().getSerializable(TrainingCreatingActivity.TRAINING);
            plannedTrains.add(training);
            adapter.notifyDataSetChanged();
            Toast.makeText(getApplicationContext(), "Тренировка создана", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(getApplicationContext(), "Тренировка не создана", Toast.LENGTH_SHORT).show();
    }
}

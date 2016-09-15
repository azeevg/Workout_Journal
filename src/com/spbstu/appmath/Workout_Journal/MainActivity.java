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
import android.widget.*;

import java.io.IOException;
import java.util.List;

/**
 * Activity with list of planned trainings
 */
public class MainActivity extends Activity {

    public static final String TRAINING_NAME = "trainingName";
    public static final String TRAINING_ID = "training_id";
    public static final int TRAINING_CREATING = 0;
    public static final int TRAINING_PREVIEW = 1;
    public static final int maxTrainingsAmount = 20;

    DBHelper db;
    List<Workout> plannedWorkouts;
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

        displayPlannedTrainings();

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

    private void displayPlannedTrainings() {
        plannedWorkouts = db.getAllPlannedWorkouts();
        ListView listView = (ListView) findViewById(R.id.trainList);
        adapter = new MainListAdapter(this, R.layout.main_list_item, plannedWorkouts, listView, db);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent intent = new Intent(getApplicationContext(), WorkoutPreviewActivity.class);
                intent.putExtra(TRAINING_NAME, plannedWorkouts.get(position).getName());
                intent.putExtra(TRAINING_ID, plannedWorkouts.get(position).getId());
                startActivityForResult(intent, TRAINING_PREVIEW);
            }
        });

        ImageButton addButton = (ImageButton) findViewById(R.id.button_add);
        if (plannedWorkouts.size() >= maxTrainingsAmount)
            addButton.setVisibility(View.INVISIBLE);
        else
            addButton.setVisibility(View.VISIBLE);
    }

    private void createNewTraining(final View view) {
        final Intent intent = new Intent(this, WorkoutCreatingActivity.class);
        final EditText editText = (EditText) view.findViewById(R.id.editName);
        intent.putExtra(TRAINING_NAME, editText.getText().toString());
        startActivityForResult(intent, TRAINING_CREATING);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TRAINING_CREATING) {
            if (resultCode == RESULT_OK) {
                Workout workout = (Workout) data.getExtras().getSerializable(WorkoutCreatingActivity.TRAINING);
                plannedWorkouts.add(workout);
                ImageButton addButton = (ImageButton) findViewById(R.id.button_add);
                if (plannedWorkouts.size() >= maxTrainingsAmount)
                    addButton.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "Тренировка создана", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(getApplicationContext(), "Тренировка не создана", Toast.LENGTH_SHORT).show();
        }
        if (requestCode == TRAINING_PREVIEW && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), "Тренировка завершена", Toast.LENGTH_SHORT).show();
        }
    }
}

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

import java.io.IOException;
import java.util.List;


public class MainActivity extends Activity {

    private static final String EXTRA_MESSAGE = "com.spbstu.appmath.Workout_Journal";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Activity activity = this;

        setContentView(R.layout.main);

        // create db in app's folder if not exist
        DBHelper dbHelper = new DBHelper(this);
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final List<Training> trainList = displayListView();

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
                                /*final EditText editText = (EditText) dialogView.findViewById(R.id.editName);
                                Toast.makeText(getApplicationContext(), editText.getText().toString(), Toast.LENGTH_SHORT).show();*/
                                createNewTraining(dialogView);
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

        final ImageButton historyButton = (ImageButton) findViewById(R.id.historyButton);
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(intent);
            }
        });
    }

    private List<Training> displayListView() {
        DBHelper db = new DBHelper(this);
        final List<Training> plannedTrains = db.getAllPlannedTrainings();
        ListView listView = (ListView) findViewById(R.id.trainList);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new MainListAdapter(this, R.layout.list_item, plannedTrains, listView, db));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO: 19.06.2016 TrainingActivity
                        
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return plannedTrains;
    }

    private void createNewTraining(final View view) {
        final Intent intent = new Intent(this, TrainingCreatingActivity.class);
        final EditText editText = (EditText) view.findViewById(R.id.editName);
        intent.putExtra(EXTRA_MESSAGE, editText.getText().toString());
        startActivity(intent);
    }
}

package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class HistoryListAdapter extends ArrayAdapter<Training> {

    final private List<Training> trainings;
    private final ListView listView;
    private final DBHelper db;

    HistoryListAdapter(Context context, int resource, List<Training> trains,
                    final ListView listView, final DBHelper db) {
        super(context, resource, trains);
//        this.trainings = new CopyOnWriteArrayList<>();
//        this.trainings.addAll(trains);
        this.trainings = trains;
        this.listView = listView;
        this.db = db;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Training.ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.history_list_item, null);

            holder = new Training().getViewHolder((TextView) convertView.findViewById(R.id.historyItemName),
                    (TextView) convertView.findViewById(R.id.historyItemDate),
                    (CheckBox) convertView.findViewById(R.id.historyCheckBox));

            convertView.setTag(holder);

            final View finalConvertView = convertView;
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    final Training training = (Training) cb.getTag();
                    training.setChecked(cb.isChecked());

                    final ImageButton deleteButton = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                    deleteButton.setVisibility(View.VISIBLE);
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Training t : trainings) {
                                if (t.isChecked() && db.deleteDoneTraining(t)) {
                                    trainings.remove(t);
                                    notifyDataSetChanged();
                                }
                            }
                            checkSelections(deleteButton);
                        }
                    });

                    if (cb.isChecked()) {
                        deleteButton.setVisibility(View.VISIBLE);
                    } else {
                        checkSelections(deleteButton);
                    }
                }
            });
        } else {
            holder = (Training.ViewHolder) convertView.getTag();
        }

        Training training = trainings.get(position);
        holder.getName().setText(training.getName());
        holder.getDate().setText(training.getDate());
        holder.getCheckBox().setChecked(training.isChecked());
        holder.getCheckBox().setTag(training);

        return convertView;
    }

    private void checkSelections(final ImageButton deleteButton) {
        for (Training t : trainings) {
            if (t.isChecked()) {
                return;
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
    }
}

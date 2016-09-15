package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.List;

public class HistoryListAdapter extends ArrayAdapter<Workout> {

    final private List<Workout> workouts;
    private final ListView listView;
    private final DBHelper db;

    HistoryListAdapter(Context context, int resource, List<Workout> trains,
                    final ListView listView, final DBHelper db) {
        super(context, resource, trains);
//        this.workouts = new CopyOnWriteArrayList<>();
//        this.workouts.addAll(trains);
        this.workouts = trains;
        this.listView = listView;
        this.db = db;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Workout.ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.history_list_item, null);

            holder = new Workout().getViewHolder((TextView) convertView.findViewById(R.id.historyItemName),
                    (TextView) convertView.findViewById(R.id.historyItemDate),
                    (CheckBox) convertView.findViewById(R.id.historyCheckBox));

            convertView.setTag(holder);

            final View finalConvertView = convertView;
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    final Workout workout = (Workout) cb.getTag();
                    workout.setChecked(cb.isChecked());

                    final ImageButton deleteButton = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                    deleteButton.setVisibility(View.VISIBLE);
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Workout t : workouts) {
                                if (t.isChecked() && db.deleteDoneWorkout(t)) {
                                    workouts.remove(t);
                                    notifyDataSetChanged();
                                }
                            }
                            setButtonsVisibility(deleteButton);
                        }
                    });

                    if (cb.isChecked()) {
                        deleteButton.setVisibility(View.VISIBLE);
                    } else {
                        setButtonsVisibility(deleteButton);
                    }
                }
            });
        } else {
            holder = (Workout.ViewHolder) convertView.getTag();
        }

        Workout workout = workouts.get(position);
        holder.getName().setText(workout.getName());
        holder.getDate().setText(workout.getDate());
        holder.getCheckBox().setChecked(workout.isChecked());
        holder.getCheckBox().setTag(workout);

        return convertView;
    }

    private void setButtonsVisibility(final ImageButton deleteButton) {
        for (Workout t : workouts) {
            if (t.isChecked()) {
                return;
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
    }
}

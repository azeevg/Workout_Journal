package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;

public class MainListAdapter extends ArrayAdapter<Training> {

    final private ArrayList<Training> trainings;
    final ListView listView;

    public MainListAdapter(Context context, int resource, ArrayList<Training> trains, ListView listView) {
        super(context, resource, trains);
        this.trainings = new ArrayList<>();
        this.trainings.addAll(trains);
        this.listView = listView;
    }

    private class ViewHolder {
        TextView name;
        TextView date;
        CheckBox checked;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.list_item, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.listItem);
            holder.checked = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            final View finalConvertView = convertView;
            holder.checked.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    Training training = (Training) cb.getTag();
                    training.setChecked(cb.isChecked());


                    final ImageButton button = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                    button.setVisibility(View.VISIBLE);

                    if (cb.isChecked()) {
                        button.setVisibility(View.VISIBLE);

                    } else {
                        boolean isAnyChecked = false;
                        for (Training t : trainings) {
                            if (t.isChecked()) {
                                isAnyChecked = true;
                                break;
                            }
                        }
                        if (!isAnyChecked) {
                            button.setVisibility(View.INVISIBLE);
                        }
                    }
                }
            });
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Training training = trainings.get(position);
        holder.name.setText(training.getName());
        holder.checked.setChecked(training.isChecked());
        holder.checked.setTag(training);

        return convertView;
    }
}

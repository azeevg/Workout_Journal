package com.spbstu.appmath.Workout_Journal;


import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

import static com.spbstu.appmath.Workout_Journal.Set.*;

public class ActiveTrainingPageAdapter extends ArrayAdapter<Set> {

    private final List<Set> sets;
    private final ListView listView;

    public ActiveTrainingPageAdapter(Context context, int resource, List<Set> sets,
                                     final ListView listView) {
        super(context, resource, sets);
        this.sets = sets;
        this.listView = listView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Set.ViewHolder holder;

        if (convertView == null) {
            convertView = vi.inflate(R.layout.workout_list_item, null);
            holder = new Set().newViewHolder((EditText) convertView.findViewById(R.id.editWeight),
                    (EditText) convertView.findViewById(R.id.editReps));
            convertView.setTag(holder);
        }
        else
            holder = (Set.ViewHolder) convertView.getTag();

        final Set set = sets.get(position);
        final EditText etWeight = holder.getEtWeight();
        final EditText etTimes = holder.getEtTimes();

        if (set.isChecked()) {
            etWeight.setText(((Double)set.getWeight()).toString());
            etTimes.setText(((Integer)set.getTimes()).toString());
        }
        else {
            etWeight.setHint(((Double)set.getWeight()).toString());
            etTimes.setHint(((Integer)set.getTimes()).toString());
        }

        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                String timesStr = etTimes.getText().toString();
                if (!timesStr.equals("")) {
                    set.setChecked(true);
                    int times = Integer.parseInt(timesStr);
                    double weight = Double.parseDouble(s.toString());
                    if (position < sets.size()) {
                        sets.get(position).setWeight(weight);
                        sets.get(position).setTimes(times);
                    }
                    else
                        sets.add(new Set(set.getExercise(), weight, times));
                }
            }
        });

        etTimes.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                String weightStr = etWeight.getText().toString();
                set.setChecked(true);
                int times = Integer.parseInt(s.toString());
                double weight = 0.0;
                if (!weightStr.equals(""))
                    weight = Double.parseDouble(weightStr);
                if (position < sets.size()) {
                    sets.get(position).setWeight(weight);
                    sets.get(position).setTimes(times);
                }
                else
                    sets.add(new Set(set.getExercise(), weight, times));
            }
        });

        return convertView;
    }
}

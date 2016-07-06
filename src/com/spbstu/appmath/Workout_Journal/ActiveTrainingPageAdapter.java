package com.spbstu.appmath.Workout_Journal;


import android.app.AlertDialog;
import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
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

    private final static int MAX_SETS_AMOUNT = 20;

    private final List<Set> sets;
    private final List<Set> setsPlanned = new ArrayList<>();
    private final ListView listView;

    public ActiveTrainingPageAdapter(Context context, int resource, List<Set> sets,
                                     final ListView listView) {
        super(context, resource, sets);
        this.sets = sets;
        this.setsPlanned.addAll(sets);
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

        etWeight.setFilters(new InputFilter[]{ new SetWeightFilter() });
        etWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {
                Editable timesText = etTimes.getText();
                if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(timesText)) {
                    set.setChecked(true);
                    int times = Integer.parseInt(timesText.toString());
                    double weight = Double.parseDouble(s.toString());
                    set.setWeight(weight);
                    set.setTimes(times);
                }
                else {
                    set.setChecked(false);
                    if (position < setsPlanned.size()) {
                        set.setWeight(setsPlanned.get(position).getWeight());
                        set.setTimes(setsPlanned.get(position).getTimes());
                    }
                    else {
                        set.setWeight(0);
                        set.setTimes(0);
                    }
                }
            }
        });

        etTimes.setFilters(new InputFilter[]{ new SetTimesFilter() });
        etTimes.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (position == sets.size() - 1 && sets.size() < MAX_SETS_AMOUNT) {
                    sets.add(new Set(set.getExercise(), 0, 0, false));
                    notifyDataSetChanged();
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
                if (!TextUtils.isEmpty(s)) {
                    Editable weightText = etWeight.getText();
                    set.setChecked(true);
                    int times = Integer.parseInt(s.toString());
                    double weight = 0.0;
                    if (!TextUtils.isEmpty(weightText))
                        weight = Double.parseDouble(weightText.toString());
                    set.setWeight(weight);
                    set.setTimes(times);
                }
                else {
                    set.setChecked(false);
                    if (position < setsPlanned.size()) {
                        set.setWeight(setsPlanned.get(position).getWeight());
                        set.setTimes(setsPlanned.get(position).getTimes());
                    }
                    else {
                        set.setWeight(0);
                        set.setTimes(0);
                    }
                }
            }
        });

        return convertView;
    }
}

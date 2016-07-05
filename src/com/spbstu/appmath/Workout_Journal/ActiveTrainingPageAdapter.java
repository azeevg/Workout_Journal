package com.spbstu.appmath.Workout_Journal;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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

        Set set = sets.get(position);
        holder.getEtWeight().setHint(((Double)set.getWeight()).toString());
        holder.getEtTimes().setHint(((Integer)set.getTimes()).toString());

        return convertView;
    }
}

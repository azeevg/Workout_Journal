package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class SetsListAdapter extends ArrayAdapter<Set> {

    private final List<Set> sets;
    private final ListView listView;

    public SetsListAdapter(Context context, int resource, List<Set> _sets,
                           final ListView _listView) {
        super(context, resource, _sets);
        sets = _sets;
        listView = _listView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Set.ViewHolder holder;
        if (convertView == null) {
            convertView = vi.inflate(R.layout.set_list_item, null);
            holder = new Set().getViewHolder((TextView) convertView.findViewById(R.id.textViewReps),
                    (TextView) convertView.findViewById(R.id.textViewWeight));
            convertView.setTag(holder);
        }
        else
            holder = (Set.ViewHolder) convertView.getTag();

        Set set = sets.get(position);
        holder.getWeight().setText(((Double)set.getWeight()).toString() + " кг");
        holder.getReps().setText(((Integer)set.getReps()).toString());

        return convertView;
    }

}

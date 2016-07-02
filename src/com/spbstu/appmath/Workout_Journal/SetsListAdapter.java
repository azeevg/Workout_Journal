package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

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
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater vi = (LayoutInflater) super.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final Set.ViewHolder holder;

        if (convertView == null) {
            convertView = vi.inflate(R.layout.sets_creating_list_item, null);
            holder = new Set().newViewHolder((TextView) convertView.findViewById(R.id.textViewWeight),
                    (TextView) convertView.findViewById(R.id.textViewReps),
                    (CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);

            final View finalConvertView = convertView;

            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    final Set set = sets.get(position);
                    set.setChecked(cb.isChecked());

                    final ImageButton deleteButton =
                            (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                    final ImageButton endButton =
                            (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_end);

                    setButtonsVisibility(deleteButton, endButton);

                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Set set : sets) {
                                if (set.isChecked()) {
                                    sets.remove(set);
                                }
                            }
                            notifyDataSetChanged();
                            setButtonsVisibility(deleteButton, endButton);
                        }
                    });
                }
            });
        }
        else
            holder = (Set.ViewHolder) convertView.getTag();

        Set set = sets.get(position);
        holder.getWeight().setText(((Double)set.getWeight()).toString());
        holder.getTimes().setText(((Integer)set.getTimes()).toString());
        holder.getCheckBox().setChecked(set.isChecked());

        return convertView;
    }

    private void setButtonsVisibility(final ImageButton deleteButton, final ImageButton endButton) {
        for (Set set : sets) {
            if (set.isChecked()) {
                endButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                return;
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
        if (sets.size() > 0)
            endButton.setVisibility(View.VISIBLE);
    }
}

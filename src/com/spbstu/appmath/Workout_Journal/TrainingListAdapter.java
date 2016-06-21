package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

public class TrainingListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<List<Reiteration>> groups;
    private final ExpandableListView listView;
    private final List<Exercise> exercises;

    public TrainingListAdapter(final Context context, final List<List<Reiteration>> groups,
                               final ExpandableListView listView) {
        this.context = context;
        this.groups = groups;
        this.listView = listView;
        this.exercises = new ArrayList<>();
    }

    @Override
    public int getGroupCount() {
        return groups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return groups.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return groups.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        final Exercise.ViewHolder holder;

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_item_removable, null);

            holder = new Exercise().newViewHolder((TextView) convertView.findViewById(R.id.exerciseName),
                    (CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);

            final View finalConvertView = convertView;
            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    final Exercise exercise = (Exercise) cb.getTag();
                    exercise.setChecked(cb.isChecked());

                    if (cb.isChecked()) {
                        final int pos = exercises.indexOf(exercise);
                        for (Reiteration r : groups.get(pos)) {
                            r.setChecked(cb.isChecked());
                        }
                    }

                    final ImageButton deleteButton = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                    deleteButton.setVisibility(View.VISIBLE);
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            for (Exercise e : exercises) {
                                if (e.isChecked()) {
                                    groups.remove(exercises.indexOf(e));
                                    exercises.remove(e);
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
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Reiteration.ViewHolder holder;

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_item_child_removable, null);

            holder = new Reiteration().newViewHolder((TextView) convertView.findViewById(R.id.textViewWeight),
                    (TextView) convertView.findViewById(R.id.textViewReiterations),
                    (CheckBox) convertView.findViewById(R.id.checkBox));
            convertView.setTag(holder);

            final View finalConvertView = convertView;
            final ImageButton deleteButton = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);

            holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    final CheckBox cb = (CheckBox) v;
                    cb.setChecked(cb.isChecked());
                    final Reiteration reiteration = (Reiteration) cb.getTag();
                    reiteration.setChecked(cb.isChecked());

                    // check if parent is needed to be checked
                    final int pos = exercises.indexOf(reiteration.getExercise());
                    if (areSame(groups.get(pos)) != 0) {
                        exercises.get(pos).setChecked(cb.isChecked());
                    }

                    deleteButton.setVisibility(View.VISIBLE);
                    deleteButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final int pos = exercises.indexOf(reiteration.getExercise());
                            final List<Reiteration> reiterationList = groups.get(pos);
                            for (Reiteration r : reiterationList) {
                                if (r.isChecked()) {
                                    reiterationList.remove(r);

                                    if (reiterationList.isEmpty()) {
                                        exercises.remove(pos);
                                        groups.remove(pos);
                                    }

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
            holder = (Reiteration.ViewHolder) convertView.getTag();
        }

        final Reiteration reiteration = groups.get(groupPosition).get(childPosition);
        holder.getWeight().setText(reiteration.getWeight());
        holder.getTimes().setText(reiteration.getTimes());
        holder.getCheckBox().setChecked(reiteration.isChecked());
        holder.getCheckBox().setTag(reiteration);


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    private void checkSelections(final ImageButton deleteButton) {
        for (List<Reiteration> reiterationList : groups) {
            for (Reiteration r : reiterationList) {
                if (r.isChecked()) {
                    return;
                }
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
    }

    private int areSame(List<Reiteration> list) {
        boolean ors = list.get(0).isChecked();
        boolean ands = ors;

        for (Reiteration r : list) {
            ors |= r.isChecked();
            ands &= r.isChecked();
        }

        if (ands)
            return 1;
        else if (ors)
            return -1;
        else
            return 0;
    }
}

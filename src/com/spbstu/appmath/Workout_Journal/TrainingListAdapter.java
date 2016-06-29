package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TrainingListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<List<Set>> groups;
    private final ExpandableListView listView;
    private final boolean isRemovable;
    private List<Exercise> exercises;

    public TrainingListAdapter(final Context context, final List<List<Set>> groups,
                               final ExpandableListView listView, final boolean isRemovable) {
        this.context = context;
        this.groups = groups;
        this.listView = listView;
        this.isRemovable = isRemovable;
        this.exercises = TrainingPreviewActivity.getExerciseList(groups);
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
        exercises = TrainingPreviewActivity.getExerciseList(groups);

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (isRemovable) {
                convertView = inflater.inflate(R.layout.expandable_list_item_removable, null);
                holder = new Exercise().newViewHolder((TextView) convertView.findViewById(R.id.exerciseName),
                        (CheckBox) convertView.findViewById(R.id.checkBox));
            } else {
                convertView = inflater.inflate(R.layout.expandable_list_item, null);
                holder = new Exercise().newViewHolder((TextView) convertView.findViewById(R.id.exerciseName));
            }

            convertView.setTag(holder);

            if (isRemovable) {
                final View finalConvertView = convertView;
                holder.getCheckBox().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final CheckBox cb = (CheckBox) v;
                        cb.setChecked(cb.isChecked());
                        final Exercise exercise = groups.get(groupPosition).get(0).getExercise();
                        exercise.setChecked(cb.isChecked());

                        /*if (cb.isChecked()) {
                            final int pos = exercises.indexOf(exercise);
                            for (Set r : groups.get(pos)) {
                                r.setChecked(cb.isChecked());
                            }
                        }*/

                        final ImageButton endButton =
                                (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_end);

                        final ImageButton deleteButton =
                                (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);

                        setButtonsVisibility(deleteButton, endButton);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Iterator<Exercise> i = exercises.iterator();
                                while(i.hasNext()) {
                                    Exercise e = i.next();
                                    groups.remove(exercises.indexOf(e));
                                    i.remove();
                                }
                                notifyDataSetChanged();
                                setButtonsVisibility(deleteButton, endButton);
                            }
                        });
                    }
                });
            }
        } else {
            holder = (Exercise.ViewHolder) convertView.getTag();
        }

        holder.getName().setText(groups.get(groupPosition).get(0).getExercise().getName());

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Set.ViewHolder holder;

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /*if (isRemovable) {
                convertView = inflater.inflate(R.layout.expandable_list_item_child_removable, null);
                holder = new Set().newViewHolder((TextView) convertView.findViewById(R.id.textViewWeight),
                        (TextView) convertView.findViewById(R.id.textViewReiterations),
                        (CheckBox) convertView.findViewById(R.id.checkBox));
            } else {*/
                convertView = inflater.inflate(R.layout.expandable_list_item_child, null);
                holder = new Set().newViewHolder((TextView) convertView.findViewById(R.id.textViewWeight),
                        (TextView) convertView.findViewById(R.id.textViewReiterations));
            //}

            convertView.setTag(holder);

            /*if (isRemovable) {
                final View finalConvertView = convertView;
                final ImageButton deleteButton = (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);

                holder.getCheckBox().setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        final CheckBox cb = (CheckBox) v;
                        cb.setChecked(cb.isChecked());
                        final Set set = (Set) cb.getTag();
                        set.setChecked(cb.isChecked());

                        // check if parent is needed to be checked
                        System.out.println(exercises);
                        System.out.println(set.getExercise());
                        final int pos = exercises.indexOf(set.getExercise());
                        if (areSame(groups.get(pos)) != 0) {
                            exercises.get(pos).setChecked(cb.isChecked());
                        }

                        deleteButton.setVisibility(View.VISIBLE);
                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                final int pos = exercises.indexOf(set.getExercise());
                                final List<Set> setList = groups.get(pos);
                                for (Set r : setList) {
                                    if (r.isChecked()) {
                                        setList.remove(r);

                                        if (setList.isEmpty()) {
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
            }*/
        } else {
            holder = (Set.ViewHolder) convertView.getTag();
        }

        final Set set = groups.get(groupPosition).get(childPosition);
        holder.getWeight().setText(String.valueOf(set.getWeight()));
        holder.getTimes().setText(String.valueOf(set.getTimes()));
        /*if (isRemovable) {
            holder.getCheckBox().setChecked(set.isChecked());
            holder.getCheckBox().setTag(set);
        }*/

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void setButtonsVisibility(final ImageButton deleteButton, final ImageButton endButton) {
        for (List<Set> setList : groups) {
            if (setList.get(0).getExercise().isChecked()) {
                endButton.setVisibility(View.INVISIBLE);
                deleteButton.setVisibility(View.VISIBLE);
                return;
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
        if (groups.size() > 0)
            endButton.setVisibility(View.VISIBLE);
    }

    private void checkSelections(final ImageButton deleteButton) {
        for (List<Set> setList : groups) {
            for (Set r : setList) {
                if (r.isChecked()) {
                    return;
                }
            }
        }
        deleteButton.setVisibility(View.INVISIBLE);
    }

    private int areSame(List<Set> list) {
        boolean ors = list.get(0).isChecked();
        boolean ands = ors;

        for (Set r : list) {
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

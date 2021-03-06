package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Iterator;
import java.util.List;

public class WorkoutListAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<List<Set>> groups;
    private final ExpandableListView listView;
    private final boolean isRemovable;
    private List<Exercise> exercises;

    public WorkoutListAdapter(final Context context, final List<List<Set>> groups,
                              final ExpandableListView listView, final boolean isRemovable) {
        this.context = context;
        this.groups = groups;
        this.listView = listView;
        this.isRemovable = isRemovable;
        this.exercises = WorkoutPreviewActivity.getExerciseList(groups);
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
        exercises = WorkoutPreviewActivity.getExerciseList(groups);

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
                        CheckBox cb = (CheckBox) v;
                        final Exercise exercise = groups.get(groupPosition).get(0).getExercise();
                        exercise.setChecked(cb.isChecked());

                        final ImageButton endButton =
                                (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_end);
                        final ImageButton deleteButton =
                                (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_delete);
                        final ImageButton addButton =
                                (ImageButton) finalConvertView.getRootView().findViewById(R.id.button_add);

                        setButtonsVisibility(deleteButton, endButton, addButton);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //List<Exercise> exercisesForRemove = new ArrayList<Exercise>();
                                Iterator<Exercise> i = exercises.iterator();
                                while(i.hasNext()) {
                                    Exercise e = i.next();
                                    if (e.isChecked()) {
                                        groups.remove(exercises.indexOf(e));
                                        i.remove();
                                    }
                                }
                                /*for (Exercise e : exercisesForRemove) {
                                    exercises.remove(e);
                                }*/
                                notifyDataSetChanged();
                                setButtonsVisibility(deleteButton, endButton, addButton);
                            }
                        });
                    }
                });
            }
        } else {
            holder = (Exercise.ViewHolder) convertView.getTag();
        }

        Exercise e = groups.get(groupPosition).get(0).getExercise();
        holder.getName().setText(e.getName());
        if (isRemovable) {
            holder.getCheckBox().setChecked(e.isChecked());
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Set.ViewHolder holder;

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_list_item_child, null);
            holder = new Set().newViewHolder((TextView) convertView.findViewById(R.id.textViewWeight),
                        (TextView) convertView.findViewById(R.id.textViewReiterations));

            convertView.setTag(holder);
        } else {
            holder = (Set.ViewHolder) convertView.getTag();
        }

        final Set set = groups.get(groupPosition).get(childPosition);
        holder.getTvWeight().setText(String.valueOf(set.getWeight()));
        holder.getTvTimes().setText(String.valueOf(set.getTimes()));

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    private void setButtonsVisibility(final ImageButton deleteButton, final ImageButton endButton,
                                      final ImageButton addButton) {
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
        if (groups.size() >= WorkoutCreatingActivity.maxExercisesAmount)
            addButton.setVisibility(View.INVISIBLE);
        else
            addButton.setVisibility(View.VISIBLE);
    }
}

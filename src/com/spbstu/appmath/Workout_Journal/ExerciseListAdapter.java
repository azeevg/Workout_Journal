package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.Serializable;
import java.util.List;

public class ExerciseListAdapter implements ListAdapter {
    private final Context context;
    private final List<Exercise> exercises;
    private final ListView listView;

    public ExerciseListAdapter(final Context context, final List<Exercise> exercises, final ListView listView) {
        this.context = context;
        this.exercises = exercises;
        this.listView = listView;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) { }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) { }

    @Override
    public int getCount() {
        return exercises.size();
    }

    @Override
    public Object getItem(int position) {
        return exercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Exercise.ViewHolder holder;

        if (convertView == null) {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item_exercises_full_list, null);

            holder = new Exercise().newViewHolder((TextView) convertView.findViewById(R.id.listItem));
            convertView.setTag(holder);
        } else {
            holder = (Exercise.ViewHolder) convertView.getTag();
        }

        final Exercise exercise = exercises.get(position);
        holder.getName().setText(exercise.getName());

        final int pos = position;
        ImageButton addExercise = (ImageButton) convertView.findViewById(R.id.button_add);
        addExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(v.getContext(), SetsCreatingActivity.class);
                intent.putExtra("exercise", (Serializable)exercises.get(pos));
                ((Activity)context).startActivityForResult(intent, 1);
            }
        });

        return convertView;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return exercises.isEmpty();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}

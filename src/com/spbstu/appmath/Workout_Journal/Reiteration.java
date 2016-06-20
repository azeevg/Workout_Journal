package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

public class Reiteration {

    private final Exercise exercise;
    private final int weight;
    private final int times;
    private boolean isChecked;
    private boolean checked;

    public ViewHolder newViewHolder(final TextView weight, final TextView times, final CheckBox checkBox) {
        return new ViewHolder(weight, times, checkBox);
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    class ViewHolder {
        TextView weight;
        TextView times;
        CheckBox checkBox;

        public ViewHolder(final TextView weight, final TextView times) {
            this.times = times;
            this.weight = weight;
        }

        public ViewHolder(final TextView weight, final TextView times, final CheckBox checkBox) {
            this.checkBox = checkBox;
            this.times = times;
            this.weight = weight;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }
    }

    public Reiteration(final Exercise exercise, final int weight, final int times) {
        this.exercise = exercise;
        this.times = times;
        this.weight = weight;
    }

    public Reiteration() {
        exercise = null;
        weight = 0;
        times = 0;
    }

    public ViewHolder newViewHolder(final TextView weight, final TextView times) {
        return new ViewHolder(weight, times);
    }

    public Exercise getExercise() {
        return exercise;
    }

    public int getTimes() {
        return times;
    }

    public int getWeight() {
        return weight;
    }
}

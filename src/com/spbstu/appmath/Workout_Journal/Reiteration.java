package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

public class Reiteration {

    private final Exercise exercise;
    private final int weight;
    private final int times;
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

        public void setCheckBox(CheckBox checkBox) {
            this.checkBox = checkBox;
        }

        public TextView getTimes() {
            return times;
        }

        public void setTimes(TextView times) {
            this.times = times;
        }

        public TextView getWeight() {
            return weight;
        }

        public void setWeight(TextView weight) {
            this.weight = weight;
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

    @Override
    public String toString() {
        return "Reiteration{" +
                "checked=" + checked +
                ", exercise=" + exercise.getName() +
                ", weight=" + weight +
                ", times=" + times +
                '}';
    }
}

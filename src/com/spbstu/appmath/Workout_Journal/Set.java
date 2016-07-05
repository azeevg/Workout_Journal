package com.spbstu.appmath.Workout_Journal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.io.Serializable;

public class Set implements Serializable {

    private final Exercise exercise;
    private final double weight;
    private final int times;
    private boolean checked;

    public Set(final Exercise exercise, final double weight, final int times) {
        this.exercise = exercise;
        this.times = times;
        this.weight = weight;
    }

    public Set() {
        exercise = null;
        weight = 0.0;
        times = 0;
    }

    public Exercise getExercise() {
        return exercise;
    }
    public int getTimes() {
        return times;
    }
    public double getWeight() {
        return weight;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
    public boolean isChecked() {
        return checked;
    }

    public ViewHolder newViewHolder(final TextView weight, final TextView times, final CheckBox checkBox) {
        return new ViewHolder(weight, times, checkBox);
    }
    public ViewHolder newViewHolder(final EditText etWeight, final EditText etTimes) {
        return new ViewHolder(etWeight, etTimes);
    }

    class ViewHolder {
        TextView tvWeight;
        EditText etWeight;
        TextView tvTimes;
        EditText etTimes;
        CheckBox checkBox;
        public ViewHolder(final TextView weight, final TextView times) {
            this.tvTimes = times;
            this.tvWeight = weight;
        }
        public ViewHolder(final TextView weight, final TextView times, final CheckBox checkBox) {
            this.checkBox = checkBox;
            this.tvTimes = times;
            this.tvWeight = weight;
        }
        public ViewHolder(final EditText etWeight, final EditText etTimes) {
            this.etTimes = etTimes;
            this.etWeight = etWeight;
        }
        public CheckBox getCheckBox() {
            return checkBox;
        }
        public TextView getTvTimes() {
            return tvTimes;
        }
        public TextView getTvWeight() {
            return tvWeight;
        }
        public EditText getEtWeight() { return etWeight; }
        public EditText getEtTimes() { return etTimes; }
    }

    public ViewHolder newViewHolder(final TextView weight, final TextView times) {
        return new ViewHolder(weight, times);
    }

    @Override
    public String toString() {
        return "Set{" +
                "checked=" + checked +
                ", exercise=" + exercise.getName() +
                ", weight=" + weight +
                ", times=" + times +
                '}';
    }
}

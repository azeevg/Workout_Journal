package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Exercise implements Serializable {
    private final String name;
    private boolean isChecked;
    private int id;

    class ViewHolder {
        TextView name;
        CheckBox checkBox;
        private int id;

        public ViewHolder(final TextView name, final CheckBox checkBox) {
            this.checkBox = checkBox;
            this.name = name;
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public TextView getName() {
            return name;
        }
    }


    public Exercise() {
        this.name = null;
    }


    public Exercise(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    public Exercise(final String name, final boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public ViewHolder newViewHolder(final TextView name) {
        return new ViewHolder(name, null);
    }

    public ViewHolder newViewHolder(final TextView name, final CheckBox checkBox) {
        return new ViewHolder(name, checkBox);
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Exercise exercise = (Exercise) o;

        if (id != exercise.id) return false;
        return name.equals(exercise.name);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + id;
        return result;
    }
}

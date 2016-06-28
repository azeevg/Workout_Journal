package com.spbstu.appmath.Workout_Journal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Exercise implements Serializable {
    private final String name;
    //private final String description;
    private boolean isChecked;
    private int id;

    public Exercise() {
        this.name = null;
//        this.description = null;
    }

    public Exercise(final String name) {
        this.name = name;
//        this.description = null;
    }

    public Exercise(final String name, final int id) {
        this.name = name;
//        this.description = null;
        this.id = id;
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

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    public ViewHolder newViewHolder(final TextView name) {
        return new ViewHolder(name, null, null);
    }

    class ViewHolder {
        TextView name;
        TextView description;
        CheckBox isChecked;
        private View checkBox;

        public ViewHolder(final TextView name, final TextView description, final CheckBox isChecked) {
            this.description = description;
            this.isChecked = isChecked;
            this.name = name;
        }

        public View getCheckBox() {
            return checkBox;
        }

        public TextView getName() {
            return name;
        }
    }

    public ViewHolder newViewHolder(final TextView name, final TextView description, final CheckBox isChecked) {
        return new ViewHolder(name, description, isChecked);
    }

    public ViewHolder newViewHolder(final TextView name, final CheckBox isChecked) {
        return new ViewHolder(name, null, isChecked);
    }


    public Exercise(final String name, /*final String description, */final boolean isChecked) {
        //this.description = description;
        this.name = name;
        this.isChecked = isChecked;
    }

    /*public String getDescription() {
        return description;
    }*/

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return isChecked;
    }
}

package com.spbstu.appmath.Workout_Journal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Exercise implements Serializable {
    private final String name;
    private boolean isChecked;
    private int id;

    public Exercise() {
        this.name = null;
    }

    public Exercise(final String name) {
        this.name = name;
    }

    public Exercise(final String name, final int id) {
        this.name = name;
        this.id = id;
    }

    public Exercise(final String name, final boolean isChecked) {
        this.name = name;
        this.isChecked = isChecked;
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

        public ViewHolder(final TextView name, final TextView description, final CheckBox isChecked) {
            this.description = description;
            this.isChecked = isChecked;
            this.name = name;
        }

        public CheckBox getCheckBox() {
            return isChecked;
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

    public String getName() {
        return name;
    }

    public boolean getCheckBox() {
        return isChecked;
    }
}

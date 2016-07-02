package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Exercise implements Serializable {
    private final String name;
    private boolean checked;
    private int id;

    public Exercise() {
        this.id = 0;
        this.name = null;
        this.checked = false;
    }

    public Exercise(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.checked = false;
    }

    public Exercise(final int id, final String name, final boolean isChecked) {
        this.id = id;
        this.name = name;
        this.checked = isChecked;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

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

    public ViewHolder newViewHolder(final TextView name) {
        return new ViewHolder(name, null);
    }
    public ViewHolder newViewHolder(final TextView name, final CheckBox checkBox) {
        return new ViewHolder(name, checkBox);
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

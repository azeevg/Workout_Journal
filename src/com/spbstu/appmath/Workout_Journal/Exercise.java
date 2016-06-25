package com.spbstu.appmath.Workout_Journal;

import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

public class Exercise {
    private final String name;
    //private final String description;
    private boolean isChecked;

    public Exercise() {
        this.name = null;
        //this.description = null;
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

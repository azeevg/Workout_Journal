package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Workout implements Serializable {
    private final String name;
    private final String date;
    private boolean checked;
    private final int id;

    public Workout() {
        name = null;
        date = null;
        id = 0;
    }

    public Workout(final int id, final String name) {
        this.id = id;
        this.name = name;
        this.date = null;
    }

    public Workout(final int id, final String name, final String date) {
        this.id = id;
        this.name = name;
        this.date = date;
    }

    public Workout(final int id, final String name, final String date, final boolean checked) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.checked = checked;
    }

    public ViewHolder getViewHolder(final TextView name, final TextView date, final CheckBox checkBox) {
        return new ViewHolder(name, date, checkBox);
    }

    public ViewHolder getViewHolder(CheckBox checkBox, TextView name) {
        return getViewHolder(name, null, checkBox);
    }

    public int getId() {
        return id;
    }


    public class ViewHolder {
        private TextView name;
        TextView date;
        CheckBox checkBox;

        public ViewHolder(final TextView name, final TextView date, final CheckBox checkBox) {
            this.checkBox = checkBox;
            this.date = date;
            this.setName(name);
        }

        public ViewHolder(final CheckBox checkBox, final TextView name) {
            this.checkBox = checkBox;
            this.setName(name);
        }

        public CheckBox getCheckBox() {
            return checkBox;
        }

        public TextView getName() {
            return name;
        }

        public TextView getDate() {
            return date;
        }

        public void setName(TextView name) {
            this.name = name;
        }
    }

        public boolean isChecked() {
        return checked;
    }

    public void setChecked(final boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return name + ", " + date;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }
}

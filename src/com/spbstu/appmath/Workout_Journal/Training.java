package com.spbstu.appmath.Workout_Journal;

import android.widget.CheckBox;
import android.widget.TextView;

import java.io.Serializable;

public class Training implements Serializable {
 //    final int id;
    final String name;
    final String date;
    private boolean checked;
    private int id;

    public Training() {
        name = null;
        date = null;
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

    public Training(final String name, final String date, final boolean checked) {
        this.name = name;
        this.date = date;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(final boolean checked) {
        this.checked = checked;
    }

    public Training(final String name, final String date) {
        this.name = name;
        this.date = date;
    }

    public Training(final String name) {
        this.name = name;
        this.date = null;
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

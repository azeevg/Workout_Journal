package com.spbstu.appmath.Workout_Journal;

public class Training {
    final String name;
    final String date;
    boolean checked;

    public Training(String name, String date, boolean checked) {
        this.name = name;
        this.date = date;
        this.checked = checked;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {

        this.checked = checked;
    }

    public Training(String name, String date) {
        this.name = name;
        this.date = date;
    }

    public Training(String name) {
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

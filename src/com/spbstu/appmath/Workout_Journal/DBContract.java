package com.spbstu.appmath.Workout_Journal;


import android.provider.BaseColumns;

public class DBContract {
    public DBContract() {
    }

    /* exercises table */
    public static abstract class Exercises implements BaseColumns {
        public static final String TABLE = "exercises";
        public static final String COLUMN_ID = "rowid";
        public static final String COLUMN_NAME = "name";
    }

    /* planned sets table */
    public static abstract class SetsPlan implements BaseColumns {
        public static final String TABLE = "sets_plan";
        public static final String COLUMN_ID = "rowid";
        public static final String COLUMN_EXERCISE_ID = "id_exercise";
        public static final String COLUMN_WORKOUT_ID = "id_workout";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_TIMES = "times";
    }

    /* done sets table */
    public static abstract class SetsDone implements BaseColumns {
        public static final String TABLE = "sets_done";
        public static final String COLUMN_ID = "rowid";
        public static final String COLUMN_EXERCISE_ID = "id_exercise";
        public static final String COLUMN_WORKOUT_ID = "id_workout";
        public static final String COLUMN_WEIGHT = "weight";
        public static final String COLUMN_TIMES = "times";
    }

    /* planned workouts table */
    public static abstract class WorkoutsPlan implements BaseColumns {
        public static final String TABLE = "workouts_plan";
        public static final String COLUMN_ID = "rowid";
        public static final String COLUMN_NAME = "name";
    }

    /* done workouts table */
    public static abstract class WorkoutsDone implements BaseColumns {
        public static final String TABLE = "workouts_done";
        public static final String COLUMN_ID = "rowid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_DATE = "date";
    }

    /* help table */
    public static abstract class Help implements BaseColumns {
        public static final String TABLE = "help";
        public static final String COLUMN_HELP_TEXT = "help_text";
    }
}

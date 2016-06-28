package com.spbstu.appmath.Workout_Journal;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;


public class ExerciseParcelable implements Parcelable{

    private List<Set> sets;

    public List<Set> getSets() {
        return sets;
    }

    public ExerciseParcelable(List<Set> _sets) {
        sets = _sets;
    }

    protected ExerciseParcelable(Parcel in) {
        sets = new ArrayList<>();
        int count = in.readInt();
        for (int i = 0; i < count; ++i) {
            int reps = in.readInt();
            double weight = in.readDouble();
            //sets.add(new Set(reps, weight));
        }
    }

    public static final Creator<ExerciseParcelable> CREATOR = new Creator<ExerciseParcelable>() {
        @Override
        public ExerciseParcelable createFromParcel(Parcel in) {
            return new ExerciseParcelable(in);
        }

        @Override
        public ExerciseParcelable[] newArray(int size) {
            return new ExerciseParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        int count = sets.size();
        dest.writeInt(count);
        for (Set set: sets) {
            dest.writeInt(set.getTimes());
            dest.writeDouble(set.getWeight());
        }
    }
}


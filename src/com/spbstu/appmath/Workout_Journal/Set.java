package com.spbstu.appmath.Workout_Journal;

import android.view.View;
import android.widget.TextView;

public class Set {
    private final int reps;
    private final double weight;

    public Set() {
        reps = 0;
        weight = 0.0;
    }
    public Set (final int _reps, final double _weight) {
        reps = _reps;
        weight = _weight;
    }

    public int getReps() {return reps;}
    public double getWeight() {return weight;}

    public ViewHolder getViewHolder(TextView _reps, TextView _weight) {
        return new ViewHolder(_reps, _weight);
    }

    public class ViewHolder {
        TextView reps, weight;
        public ViewHolder(final TextView _reps, final TextView _weight) {
            reps = _reps;
            weight = _weight;
        }
        public TextView getReps() {
            return reps;
        }
        public TextView getWeight() {
            return weight;
        }
    }

}

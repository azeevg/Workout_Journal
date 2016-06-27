package com.spbstu.appmath.Workout_Journal;


public class Set {
    private final int reps;
    private final double weight;
    public Set (final int _reps, final double _weight) {
        reps = _reps;
        weight = _weight;
    }
    public int getReps() {return reps;}
    public double getWeight() {return weight;}
}

package com.spbstu.appmath.Workout_Journal;


import android.text.InputFilter;
import android.text.Spanned;

public class SetWeightFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int dotIndex = -1;
        for (int i = start; i < end; ++i) {
            if (!Character.isDigit(source.charAt(i))) {
                if (source.charAt(i) != '.' || dest.toString().indexOf('.') >= 0)
                    return "";
                else {
                    dotIndex = dend + i;
                }
            }
        }
        if (dotIndex == -1)
            dotIndex = dest.toString().indexOf('.');

        // more than 2 digits after dot
        if (dotIndex >= 0) {
            if (end + dend - dotIndex - 1 > 2)
                return "";
        }

        double weight = Double.parseDouble(dest.toString() + source.toString());
        if (weight > 1000 || weight < 1)
            return "";
        return null;
    }
}
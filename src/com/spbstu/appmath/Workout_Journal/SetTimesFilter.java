package com.spbstu.appmath.Workout_Journal;


import android.text.InputFilter;
import android.text.Spanned;

public class SetTimesFilter implements InputFilter {

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        for (int i = start; i < end; ++i)
            if (!Character.isDigit(source.charAt(i)))
                return "";
        int times = Integer.parseInt(dest.toString() + source.toString());
        if (times > 100 || times < 1)
            return "";
        return null;
    }
}
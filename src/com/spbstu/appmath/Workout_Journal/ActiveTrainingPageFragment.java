package com.spbstu.appmath.Workout_Journal;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ActiveTrainingPageFragment extends Fragment {

    static final String PAGE_NUMBER = "pageNumber";
    static final String PAGE_AMOUNT = "pageAmount";
    static final String SETS = "setsInCurrentExercise";

    private int pageNumber;
    private int pageAmount;
    private List<Set> sets;

    static ActiveTrainingPageFragment newInstance(int pageNumber, int pageAmount, List<Set> sets) {
        ActiveTrainingPageFragment pageFragment = new ActiveTrainingPageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_NUMBER, pageNumber);
        arguments.putInt(PAGE_AMOUNT, pageAmount);
        arguments.putSerializable(SETS, (Serializable) sets);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(PAGE_NUMBER);
        pageAmount = getArguments().getInt(PAGE_AMOUNT);
        sets = (List<Set>) getArguments().getSerializable(SETS);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout, container, false);

        TextView title = (TextView) rootView.findViewById(R.id.textView);
        title.setText(sets.get(0).getExercise().getName());

        displaySets(rootView);
        setButtonsVisibility(rootView);

        return rootView;
    }

    private void displaySets(ViewGroup rootView) {
        final ListView listView = (ListView) rootView.findViewById(R.id.exercisesList);
        ArrayAdapter<Set> adapter =
                new ActiveTrainingPageAdapter(getContext(), R.layout.workout_list_item, sets, listView);
        listView.setAdapter(adapter);
    }

    private void setButtonsVisibility(ViewGroup rootView) {
        ImageButton prevButton = (ImageButton) rootView.findViewById(R.id.prevButton);
        ImageButton nextButton = (ImageButton) rootView.findViewById(R.id.nextButton);
        ImageButton finishButton = (ImageButton) rootView.findViewById(R.id.finishButton);
        if (pageNumber == 0) {                          // first exercise
            prevButton.setVisibility(View.INVISIBLE);
            if (pageNumber == pageAmount - 1) {         // first and last (unique) exercise
                nextButton.setVisibility(View.INVISIBLE);
                finishButton.setVisibility(View.VISIBLE);
            }
            else {                                      // first but not last exercise
                nextButton.setVisibility(View.VISIBLE);
                finishButton.setVisibility(View.INVISIBLE);
            }
        }
        else if (pageNumber == pageAmount - 1) {        // last exercise
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
            finishButton.setVisibility(View.VISIBLE);
        }
        else {                                          // transitional exercise
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.INVISIBLE);
        }
    }

    public List<Set> getSetsDone() {
        List<Set> setsDone = new ArrayList<>();
        for (Set set : sets) {
            if (set.isChecked())
                setsDone.add(set);
        }
        if (setsDone.size() == 0) {
            setsDone.add(new Set(sets.get(0).getExercise(), 0, 0, false));
        }
        return setsDone;
    }

}

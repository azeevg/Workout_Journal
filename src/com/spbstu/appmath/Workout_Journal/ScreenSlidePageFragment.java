package com.spbstu.appmath.Workout_Journal;

import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ScreenSlidePageFragment extends Fragment {

    static final String PAGE_NUMBER = "pageNumber";
    static final String PAGE_AMOUNT = "pageAmount";

    int pageNumber;
    int pageAmount;

    static ScreenSlidePageFragment newInstance(int pageNumber, int pageAmount) {
        ScreenSlidePageFragment pageFragment = new ScreenSlidePageFragment();
        Bundle arguments = new Bundle();
        arguments.putInt(PAGE_NUMBER, pageNumber);
        arguments.putInt(PAGE_AMOUNT, pageAmount);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = getArguments().getInt(PAGE_NUMBER);
        pageAmount = getArguments().getInt(PAGE_AMOUNT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.workout, container, false);

        ImageButton prevButton = (ImageButton) rootView.findViewById(R.id.prevButton);
        ImageButton nextButton = (ImageButton) rootView.findViewById(R.id.nextButton);
        ImageButton finishButton = (ImageButton) rootView.findViewById(R.id.finishButton);

        if (pageNumber == 0) {
            prevButton.setVisibility(View.INVISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.INVISIBLE);
        }
        else if (pageNumber == pageAmount - 1) {
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.INVISIBLE);
            finishButton.setVisibility(View.VISIBLE);
        }
        else {
            prevButton.setVisibility(View.VISIBLE);
            nextButton.setVisibility(View.VISIBLE);
            finishButton.setVisibility(View.INVISIBLE);
        }

        return rootView;
    }
}

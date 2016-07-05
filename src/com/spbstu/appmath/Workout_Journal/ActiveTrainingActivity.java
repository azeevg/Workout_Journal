package com.spbstu.appmath.Workout_Journal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;

/**
 * Slider with a list of sets of an exercise which user planned to do and
 * with fields for inserting user's results.
 */
public class ActiveTrainingActivity extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private int numPages;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;

    /**
     * List of sets grouped in sublists by their exercises
     */
    private List<List<Set>> groupedSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_pager);

        groupedSets = (List<List<Set>>)
                getIntent().getExtras().getSerializable(TrainingPreviewActivity.TRAINING);
        numPages = groupedSets.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * prevButton handler
     */
    public void jumpToPrevPage(View view) {
        mPager.setCurrentItem(mPager.getCurrentItem() - 1, true);
    }

    /**
     * nextButton handler
     */
    public void jumpToNextPage(View view) {
        mPager.setCurrentItem(mPager.getCurrentItem() + 1, true);
    }


    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ActiveTrainingPageFragment.newInstance(position, numPages, groupedSets.get(position));
        }

        @Override
        public int getCount() {
            return numPages;
        }

    }
}

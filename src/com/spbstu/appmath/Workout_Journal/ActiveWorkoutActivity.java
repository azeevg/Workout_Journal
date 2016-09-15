package com.spbstu.appmath.Workout_Journal;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Slider with a list of sets of an exercise which user planned to do and
 * with fields for inserting user's results.
 */
public class ActiveWorkoutActivity extends FragmentActivity {

    public static final String SETS_DONE = "setsDone";

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
    private ScreenSlidePagerAdapter mPagerAdapter;

    /**
     * List of planned sets grouped in sublists by their exercises
     */
    private List<List<Set>> groupedSetsPlanned;

    /**
     * List of done sets grouped in sublists by their exercises
     */
    private List<List<Set>> gropedSetsDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_pager);

        groupedSetsPlanned = (List<List<Set>>)
                getIntent().getExtras().getSerializable(WorkoutPreviewActivity.SETS_PLANNED);
        gropedSetsDone = new ArrayList<>();
        numPages = groupedSetsPlanned.size();

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        mPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    int prevPosition = 0;
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }
                    @Override
                    public void onPageSelected(int position) {
                        onPageChange(prevPosition);
                        final InputMethodManager imm = (InputMethodManager)getSystemService(
                                Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(mPager.getWindowToken(), 0);
                    }
                    @Override
                    public void onPageScrollStateChanged(int state) {
                        if (state == ViewPager.SCROLL_STATE_IDLE)
                            prevPosition = mPager.getCurrentItem();
                    }
                });
    }

    @Override
    public void onBackPressed() {
        finishTraining(null);
    }

    /**
     * Saves all done sets in gropedSetsDone
     * @param prevIndex - index of previous active page
     */
    private void onPageChange(int prevIndex) {
        ActiveTrainingPageFragment page = mPagerAdapter.getItem(prevIndex);
        List<Set> setsDone = page.getSetsDone();
        if (prevIndex >= gropedSetsDone.size())
            gropedSetsDone.add(setsDone);
        else {
            gropedSetsDone.remove(prevIndex);
            gropedSetsDone.add(prevIndex, setsDone);
        }
    }

    /**
     * prevButton handler
     */
    public void jumpToPrevPage(View view) {
        int index = mPager.getCurrentItem();
        onPageChange(index);
        mPager.setCurrentItem(index - 1, true);
    }

    /**
     * nextButton handler
     */
    public void jumpToNextPage(View view) {
        int index = mPager.getCurrentItem();
        onPageChange(index);
        mPager.setCurrentItem(index + 1, true);
    }

    /**
     * finishButton handler
     * Shows dialog offering to finish training.
     */
    public void finishTraining(View view) {
        onPageChange(mPager.getCurrentItem());

        final AlertDialog.Builder builder = new AlertDialog.Builder(ActiveWorkoutActivity.this);
        final LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_finish_training, null);

        builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        })
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(SETS_DONE, (Serializable)gropedSetsDone);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                });
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        dialog.setView(dialogView, 0, 0, 0, 0);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                final View v = (View) dialog.getButton(DialogInterface.BUTTON_NEGATIVE).getParent();
                v.setBackgroundColor(getResources().getColor(R.color.light_gray_1));
            }
        });
        dialog.show();
    }


    private class ScreenSlidePagerAdapter extends FragmentPagerAdapter {

        List<ActiveTrainingPageFragment> pages = new ArrayList<>();

        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
            for (int i = 0; i < getCount(); ++i) {
                pages.add(ActiveTrainingPageFragment.newInstance(i, numPages, groupedSetsPlanned.get(i)));
            }
        }

        @Override
        public ActiveTrainingPageFragment getItem(int position) {
            return pages.get(position);
        }

        @Override
        public int getCount() {
            return numPages;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return groupedSetsPlanned.get(position).get(0).getExercise().getName();
        }

    }
}

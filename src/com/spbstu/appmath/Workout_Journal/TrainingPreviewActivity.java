package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class TrainingPreviewActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_preview);

        try {
            displayExpandableList();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private List<List<Reiteration>> displayExpandableList() throws InstantiationException, IllegalAccessException {
        // TODO: 25.06.2016 DATABASE: вытащить из БД все подходы
        final List<Reiteration> reiterationList = new ArrayList<>();
        reiterationList.add(new Reiteration(new Exercise("Упражнение 1", 1), 50, 4));
        reiterationList.add(new Reiteration(new Exercise("Упражнение 2", 2), 60, 3));
        reiterationList.add(new Reiteration(new Exercise("Упражнение 2", 2), 60, 4));
        reiterationList.add(new Reiteration(new Exercise("Упражнение 1", 1), 30, 4));
        reiterationList.add(new Reiteration(new Exercise("Упражнение 4", 4), 20, 4));
        reiterationList.add(new Reiteration(new Exercise("Упражнение 4", 4), 40, 2));

        final List<List<Reiteration>> groups = mockGetReiterations(reiterationList);

        final ExpandableListView listView = (ExpandableListView) findViewById(R.id.previewExpandableListView);
        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new TrainingListAdapter(this, groups, listView, false));

        return groups;
    }

    private List<List<Reiteration>> mockGetReiterations(final List<Reiteration> reiterations) throws IllegalAccessException, InstantiationException {
        final List<List<Reiteration>> groups = new CopyOnWriteArrayList<>();
        final List<Exercise> exerciseList = new LinkedList<>();

        for (Reiteration reiteration : reiterations) {
            int index = exerciseList.indexOf(reiteration.getExercise());

            if (index == -1) {
                exerciseList.add(reiteration.getExercise());
                groups.add(new CopyOnWriteArrayList<Reiteration>());
                index = exerciseList.size() - 1;
            }

            groups.get(index).add(reiteration);
        }

        return groups;
    }

    public static List<Exercise> getExerciseList(final List<List<Reiteration>> groups) {
        final List<Exercise> exerciseList = new LinkedList<>();

        for (List<Reiteration> group : groups) {
            for (Reiteration reiteration : group) {
                if (!exerciseList.isEmpty() && !exerciseList.contains(reiteration.getExercise())) {
                    exerciseList.add(reiteration.getExercise());
                    System.out.println(reiteration.getExercise().getName());
                }
            }
        }
        return exerciseList;
    }
}

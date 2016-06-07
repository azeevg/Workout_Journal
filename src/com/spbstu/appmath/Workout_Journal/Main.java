package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.ArrayList;


public class Main extends Activity {

    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String WEIGHT = "weight";
    public static final String INNER_LIST = "inner_list";
    public static final String REPS = "reps";
    final String[] exercises = new String[]{"Упражнение длинное название 1", "Упражнение покороче 2", "Упражнение 3", "Упражнение 4"};
    final String[][] attributes = new String[][]{{"80", "7"}, {"80", "7"}, {"80", "6"}, {"80", "5"}};
    final String[] weights = new String[]{"100", "110", "100", "100", "90"};
    final String[] reps = new String[]{"6", "6", "5", "3", "2"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       /* setContentView(R.layout.exercise_creating);

        Map<String, String> setParameters;

        ArrayList<Map<String, String>> setsList = new ArrayList<>();

        for (String[] pair : attributes) {
            setParameters = new HashMap<>();
            setParameters.put(WEIGHT, pair[0]);
            setParameters.put(REPS, pair[1]);
            setsList.add(setParameters);
        }

        String from[] = new String[]{WEIGHT, REPS};
        int to[] = new int[]{R.id.textViewWeight, R.id.textViewReiterations};

        SimpleAdapter adapter = new SimpleAdapter(this, setsList, R.layout.sets_list_item, from, to);

        ListView listView = (ListView) findViewById(R.id.setsList);
        listView.setAdapter(adapter);*/


/*        setContentView(R.layout.workout_creating);
        Map<String, String> map;

        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();

        for (String group : exercises) {
            map = new HashMap<>();
            map.put(NAME, group);
            groupDataList.add(map);
        }

        String groupFrom[] = new String[]{NAME};
        int groupTo[] = new int[]{R.id.exerciseName};

        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();
        ArrayList<Map<String, String>> сhildDataItemList = null;

        for (String exercise : exercises) {
            сhildDataItemList = new ArrayList<>();

            for (String[] pair : attributes) {
                map = new HashMap<>();
                map.put(WEIGHT, pair[0]);
                map.put(REPS, pair[1]);
                сhildDataItemList.add(map);
            }

            сhildDataList.add(сhildDataItemList);
        }

        String childFrom[] = new String[]{WEIGHT, REPS};
        int childTo[] = new int[]{R.id.textViewWeight, R.id.textViewReiterations};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this,
                groupDataList, R.layout.expandable_list_item_removable, groupFrom, groupTo,
                сhildDataList, R.layout.expandable_list_item_child_removable, childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.workoutExpandableListView);

        expandableListView.setAdapter(adapter);*/

      /*  setContentView(R.layout.workout_first_exercise);

        ListView listView = (ListView) findViewById(R.id.exercisesList);
        final ArrayList<HashMap<String, String>> exercisesList = new ArrayList<>();
        HashMap<String, String> hm;


        for (int i = 0; i < 5; i++) {
            hm = new HashMap<>();
//            hm.put(WEIGHT, weights[i]);
//            hm.put(REPS, reps[i]);
            exercisesList.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, exercisesList,
                R.layout.exercise_list_item, new String[]{},
                new int[]{});

        listView.setAdapter(adapter);*/

        // // // // // //
    /*    setContentView(R.layout.workout_preview);

        Map<String, String> map;

        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : exercises) {
            map = new HashMap<>();
            map.put(NAME, group);
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{NAME};
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[]{R.id.textViewWeight, R.id.textViewReiterations};

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        ArrayList<Map<String, String>> сhildDataItemList = null;
        // заполняем список атрибутов для каждого элемента
        for (String exercise : exercises) {
            сhildDataItemList = new ArrayList<>();

            for (String[] pair : attributes) {
                map = new HashMap<>();
                map.put(WEIGHT, pair[0]);
                map.put(REPS, pair[1]);
                сhildDataItemList.add(map);
            }
            // добавляем в коллекцию коллекций
            сhildDataList.add(сhildDataItemList);
        }

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{WEIGHT, REPS};
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[]{R.id.textViewWeight, R.id.textViewReiterations};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupDataList,
                R.layout.history_detailed_list_item, groupFrom,
                groupTo, сhildDataList, R.layout.expandable_list_item_child,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.previewExpandableListView);
        expandableListView.setAdapter(adapter);
*/
        //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
        setContentView(R.layout.main);

        final ArrayList<Training> plannedTrains = PlannedTrains.getAll();
        ListView listView = (ListView) findViewById(R.id.trainList);

        listView.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        listView.setAdapter(new MainListAdapter(this, R.layout.list_item, plannedTrains, listView));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),
                        "Clicked on Row: " + position,
                        Toast.LENGTH_SHORT).show();
            }
        });


//        String[] trainings = getResources().getStringArray(R.array.exercises);

        /*setContentView(R.layout.workout_creating_removing);
        String[] trainings = getResources().getStringArray(R.array.exercises);
        HashMap<String, String> hm = null;
        ArrayList<Map<String, String>> workouts = new ArrayList<>();
        for (String workout : trainings) {
            hm = new HashMap<>();
            hm.put(NAME, workout);
            workouts.add(hm);
        }
        ListView listView = (ListView) findViewById(R.id.trainList);
        SimpleAdapter adapter = new SimpleAdapter(this, workouts, R.layout.list_item,
                new String[]{NAME}, new int[]{R.id.listItem});
        listView.setAdapter(adapter);*/


       /* setContentView(R.layout.main_removing);
        String[] trainings = getResources().getStringArray(R.array.trainings);
        HashMap<String, String> hm = null;
        ArrayList<Map<String, String>> workouts = new ArrayList<>();
        for (String workout : trainings) {
            hm = new HashMap<>();
            hm.put(NAME, workout);
            workouts.add(hm);
        }
        ListView listView = (ListView) findViewById(R.id.trainList);
        SimpleAdapter adapter = new SimpleAdapter(this, workouts, R.layout.list_item,
                new String[]{NAME}, new int[]{R.id.listItem});
        listView.setAdapter(adapter);*/
    }
}

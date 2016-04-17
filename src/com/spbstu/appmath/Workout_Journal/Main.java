package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Main extends Activity {

    public static final String NAME = "name";
    public static final String DATE = "date";
    public static final String WEIGHT = "weight";
    public static final String REITERATIONS = "reiterations";
    final String[] exercises = new String[]{"Упражнение длинное название 1", "Упражнение покороче 2", "Упражнение 3", "Упражнение 4"};
    final String[][] attributes = new String[][]{{"80", "7"}, {"80", "7"}, {"80", "6"}, {"80", "5"}};
    final String[] weights = new String[]{"100", "110", "100", "100", "90"};
    final String[] reiterations = new String[]{"6", "6", "5", "3", "2"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout_started);

        ListView listView = (ListView) findViewById(R.id.exercisesList);
        final ArrayList<HashMap<String, String>> exercisesList = new ArrayList<>();
        HashMap<String, String> hm;


        for (int i = 0; i < 5; i++) {
            hm = new HashMap<>();
//            hm.put(WEIGHT, weights[i]);
//            hm.put(REITERATIONS, reiterations[i]);
            exercisesList.add(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, exercisesList,
                R.layout.exercise_list_item, new String[]{},
                new int[]{});

        listView.setAdapter(adapter);
        // // // // // //
        /*setContentView(R.layout.history_detailed);

        Map<String, String> map;

        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : exercises) {
            map = new HashMap<>();
            map.put("groupName", group);
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
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
                map.put(REITERATIONS, pair[1]);
                сhildDataItemList.add(map);
            }
            // добавляем в коллекцию коллекций
            сhildDataList.add(сhildDataItemList);
        }

        // список атрибутов элементов для чтения
        String childFrom[] = new String[]{WEIGHT, REITERATIONS};
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[]{R.id.textViewWeight, R.id.textViewReiterations};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupDataList,
                R.layout.history_detailed_list_item, groupFrom,
                groupTo, сhildDataList, R.layout.expandable_list_item_child,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.historyExpandableListView);
        expandableListView.setAdapter(adapter);*/

        //  //  //  //  //  //  //  //  //  //  //  //  //  //  //  //
        /*    setContentView(R.layout.history);
        ListView listView = (ListView) findViewById(R.id.historyListView);
        final ArrayList<HashMap<String, String>> historyItems = new ArrayList<>();
        HashMap<String, String> hm;

        for (int i = 1; i < 10; i++) {
            hm = new HashMap<>();
            hm.put(NAME, "Название тренировки " + i);
            hm.put(DATE, "1" + i + ".04.2016");
            historyItems.add(hm);
            System.out.println(hm);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, historyItems,
                R.layout.history_list_item, new String[]{NAME, DATE},
                new int[]{R.id.historyItemName, R.id.historyItemDate});

        listView.setAdapter(adapter);*/
//        String[] trainings = getResources().getStringArray(R.array.exercises);

        /*setContentView(R.layout.workout_creating_removing);
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

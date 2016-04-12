package com.spbstu.appmath.Workout_Journal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main extends Activity {

    public static final String NAME = "name";
    public static final String DATE = "date";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_detailed);

        final String[] groupsArray = new String[] {"Упражнение 1", "Упражнение 2", "Упражнение 3", "Упражнение 4"};
        final String[] elements = new String[] {"80 кг 7 раз", "80 кг 7 раз", "80 кг 5 раз", "80 кг 4 раза"};

        Map<String, String> map;

        ArrayList<Map<String, String>> groupDataList = new ArrayList<>();
        // заполняем коллекцию групп из массива с названиями групп

        for (String group : groupsArray) {
            // заполняем список атрибутов для каждой группы
            map = new HashMap<>();
            map.put("groupName", group); // время года
            groupDataList.add(map);
        }

        // список атрибутов групп для чтения
        String groupFrom[] = new String[] { "groupName" };
        // список ID view-элементов, в которые будет помещены атрибуты групп
        int groupTo[] = new int[] { android.R.id.text1 };

        // создаем общую коллекцию для коллекций элементов
        ArrayList<ArrayList<Map<String, String>>> сhildDataList = new ArrayList<>();

        // в итоге получится сhildDataList = ArrayList<сhildDataItemList>

        // создаем коллекцию элементов для первой группы
        ArrayList<Map<String, String>> сhildDataItemList = new ArrayList<>();
        // заполняем список атрибутов для каждого элемента
        for (String month : elements) {
            map = new HashMap<>();
            map.put("monthName", month); // название месяца
            сhildDataItemList.add(map);
        }
        // добавляем в коллекцию коллекций
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для второй группы
        сhildDataItemList = new ArrayList<>();
        for (String month : elements) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для третьей группы
        сhildDataItemList = new ArrayList<>();
        for (String month : elements) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // создаем коллекцию элементов для четвертой группы
        сhildDataItemList = new ArrayList<>();
        for (String month : elements) {
            map = new HashMap<>();
            map.put("monthName", month);
            сhildDataItemList.add(map);
        }
        сhildDataList.add(сhildDataItemList);

        // список атрибутов элементов для чтения
        String childFrom[] = new String[] { "monthName" };
        // список ID view-элементов, в которые будет помещены атрибуты
        // элементов
        int childTo[] = new int[] { R.id.text1 };

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this, groupDataList,
                R.layout.history_detailed_list_item, groupFrom,
                groupTo, сhildDataList, R.layout.expandable_list_item_child,
                childFrom, childTo);

        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.historyExpandableListView);
        expandableListView.setAdapter(adapter);

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

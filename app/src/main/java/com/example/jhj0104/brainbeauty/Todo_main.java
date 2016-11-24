package com.example.jhj0104.brainbeauty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jhj0104 on 2016-11-24.
 */

public class Todo_main extends AppCompatActivity{
    private ArrayList<Todo> todo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_main);

        //리스트 배열 넣을 곳
        todo.add(new Todo("쿠와아ㅏ아아아아아아아아아아아아아아아앙와아와와오아ㅗ아ㅗ아ㅘ와아아아앙아앙"));

        //create our new array adapter
        final ArrayAdapter<Todo> adapter = new TodoAdapter(this, 0, todo);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.todo_list);
        listView.setAdapter(adapter);

        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(listView, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);

            @Override
            public boolean canDismiss(int position) {
                return true;
            }
            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    adapter.remove(adapter.getItem(position));
                    adapter.notifyDataSetChanged();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                return;
            }
        });
        listView.setOnTouchListener(touchListener);
        listView.setOnScrollListener(touchListener.makeScrollListener());
    }
}

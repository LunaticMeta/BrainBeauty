package com.example.jhj0104.brainbeauty;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

        todo.add(new Todo("안녀여여영"));
        //create our new array adapter
        ArrayAdapter<Todo> adapter = new TodoAdapter(this, 0, todo);

        //Find list view and bind it with the custom adapter
        ListView listView = (ListView) findViewById(R.id.todo_list);
        listView.setAdapter(adapter);



    }
}

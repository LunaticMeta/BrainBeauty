package com.example.jhj0104.brainbeauty;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jhj0104 on 2016-11-24.
 */

public class TodoAdapter extends ArrayAdapter<Todo> {
    private Context context;
    private List<Todo> todoList;

    public TodoAdapter(Context context, int resource, ArrayList<Todo> object) {
        super(context, resource, object);
        this.context = context;
        this.todoList = object;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Todo todo = todoList.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.todo_item,null);

        TextView list_title = (TextView) view.findViewById(R.id.todo_item_title);
        list_title.setText("$" + String.valueOf(todo.getTitle()));

        //display trimmed excerpt for description
        int descriptionLength = todo.getTitle().length();
        if(descriptionLength >= 100){
            String descriptionTrim = todo.getTitle().substring(0, 100) + "...";
            list_title.setText(descriptionTrim);
        }else{
            list_title.setText(todo.getTitle());
        }
        return view;
    }


}

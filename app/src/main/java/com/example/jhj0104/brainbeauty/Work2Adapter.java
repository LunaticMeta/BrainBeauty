package com.example.jhj0104.brainbeauty;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
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

public class Work2Adapter extends ArrayAdapter<String> {
    private Context context;
    private List<String> todoList;

    public Work2Adapter(Context context, int resource, ArrayList<String> object) {
        super(context, resource, object);
        this.context = context;
        this.todoList = object;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        Work work2= new Work(todoList.get(position));

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_work2,null);

        TextView list_title = (TextView) view.findViewById(R.id.work_text2);
        list_title.setText("$" + String.valueOf(work2.getTitle()));
        list_title.setPaintFlags(list_title.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

        //display trimmed excerpt for description
        int descriptionLength = work2.getTitle().length();
        if(descriptionLength >= 30){
            String descriptionTrim = work2.getTitle().substring(0, 30) + "...";
            list_title.setText(descriptionTrim);
        }else{
            list_title.setText(work2.getTitle());
        }
        return view;
    }






}

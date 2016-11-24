package com.example.jhj0104.brainbeauty;

import android.content.Context;

import java.util.List;

/**
 * Created by jhj0104 on 2016-11-24.
 */

public class Todo {
    private Context context;
    private List<Todo> todoList;

    private String title;

    public Todo(String title){
        this.title = title;
    }

    //GET
    public String getTitle(){return title;}


}

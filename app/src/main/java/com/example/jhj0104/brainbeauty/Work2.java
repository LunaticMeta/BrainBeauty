package com.example.jhj0104.brainbeauty;

import android.content.Context;

import java.util.List;

/**
 * Created by jhj0104 on 2016-11-24.
 */

public class Work2 {
    private Context context;
    private List<Work2> todoList;

    private String title;

    public Work2(String title){
        this.title = title;
    }

    //GET
    public String getTitle(){return title;}


}

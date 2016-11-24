package com.example.jhj0104.brainbeauty;

import android.content.Context;

import java.util.List;

/**
 * Created by jhj0104 on 2016-11-24.
 */

public class Work1 {
    private Context context;
    private List<Work> todoList;

    private String title;

    public void Work1(String title){
        this.title = title;
    }

    //GET
    public String getTitle(){return title;}

}

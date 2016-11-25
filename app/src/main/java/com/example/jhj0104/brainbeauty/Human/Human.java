package com.example.jhj0104.brainbeauty.Human;

import android.graphics.Bitmap;

/**
 * Created by jhj0104 on 2016-11-23.
 */

public class Human {//데이터 노드
    public String name;
    public String gender;
    public String age;
    public Bitmap image;

    Human(String name, String gender, String age, Bitmap image){
        this.image = image;
        this.name =name;
        this.gender = gender;
        this.age = age;
    }
}

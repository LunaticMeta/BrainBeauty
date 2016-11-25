package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daimajia.swipe.SwipeLayout;

/**
 * Created by jhj0104 on 2016-11-21.
 */

public class D_menu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_menu);
        SwipeLayout swipeLayout =  (SwipeLayout)findViewById(R.id.sample1);

    }
    public void onClickDiaryList (View v){
        Intent myIntent = new Intent(D_menu.this, D_diaryList.class);
        D_menu.this.startActivity(myIntent);
    }
    public void onClickCreateDiary(View v){
        Intent myIntent = new Intent(D_menu.this, D_diaryAdd.class);
        D_menu.this.startActivity(myIntent);
    }
}

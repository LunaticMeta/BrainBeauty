package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

/**
 * Created by jhj0104 on 2016-11-21.
 */

public class D_diaryAdd extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_diary_add);
    }

    public void onClick_saveDiary (View view){
        Toast.makeText(getBaseContext(), "새로운 일기가 추가되었습니다.", Toast.LENGTH_SHORT).show();
        Intent myIntent = new Intent(D_diaryAdd.this, D_diaryList.class);
        D_diaryAdd.this.startActivity(myIntent);
        finish();
    }
}

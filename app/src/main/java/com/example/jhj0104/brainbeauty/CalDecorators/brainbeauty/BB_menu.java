package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BB_menu extends AppCompatActivity {

    private long lastTimeBackPressed;
    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();
    String adjustDate= (new SimpleDateFormat("yyyy.MM.dd").format(date));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb_menu);
    }
    public void onClickSchedule(View v){
        S_data data = new S_data(adjustDate, ".");
        Intent myIntent = new Intent(BB_menu.this, S_list.class);
        myIntent.putExtra("Date",data);
        BB_menu.this.startActivity(myIntent);
    }
    public void onClickDiary(View v){
        Intent myIntent = new Intent(BB_menu.this, D_menu.class);
        BB_menu.this.startActivity(myIntent);
    }
    @Override
    public void onBackPressed(){
        if(System.currentTimeMillis() - lastTimeBackPressed < 1500){
            finish();
            return;
        }
        Toast.makeText(getBaseContext(), "'뒤로'버튼을 한 번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        lastTimeBackPressed = System.currentTimeMillis();
    }
}
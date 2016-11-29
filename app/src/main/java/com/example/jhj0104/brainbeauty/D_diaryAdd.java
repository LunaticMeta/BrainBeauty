package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jhj0104 on 2016-11-21.
 */

public class D_diaryAdd extends AppCompatActivity{

    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();
    String adjustDate= (new SimpleDateFormat("yyyy.MM.dd").format(date));
    String adjustTime = (new SimpleDateFormat("HH:mm").format(date));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_diary_add);
    }

    public void onClick_saveDiary (View view){

        String etTitle = ((EditText)findViewById(R.id.editTitle)).getText().toString();
        String etContent = ((EditText) findViewById(R.id.editContent)).getText().toString();
        String etWeather = ((EditText) findViewById(R.id.editWeather)).getText().toString();

        if(etContent.isEmpty()){
            Toast.makeText(getBaseContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
            dbHelper.insert_DI(adjustDate+".",adjustTime, etTitle, etContent, etWeather, "false");
            Toast.makeText(getBaseContext(), "새로운 일기가 추가되었습니다.", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(D_diaryAdd.this, D_diaryList.class);
            D_diaryAdd.this.startActivity(myIntent);
            finish();
        }
    }
}

package com.example.jhj0104.brainbeauty;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class BB_menu extends AppCompatActivity {

    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();
    String adjustDate= (new SimpleDateFormat("yyyy.MM.dd").format(date));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bb_menu);
    }

        public void onClickSchedule(View v){
        S_data data = new S_data(adjustDate+".", "");
        Intent myIntent = new Intent(BB_menu.this, S_main.class);
        myIntent.putExtra("Date",data);
        BB_menu.this.startActivity(myIntent);
    }

    public void onClickDiary(View v){
        Intent myIntent = new Intent(BB_menu.this, D_menu.class);
        BB_menu.this.startActivity(myIntent);
    }

    @Override
    public void onBackPressed(){


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?")
                .setMessage("정말로 뇌미인 다이어리를 종료하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    //강제 전체 종료 : http://sd0720.blogspot.kr/2012/06/process-kill.html
                    finish();
                    android.os.Process.killProcess(android.os.Process.myPid());

                    return;
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기
    }
}
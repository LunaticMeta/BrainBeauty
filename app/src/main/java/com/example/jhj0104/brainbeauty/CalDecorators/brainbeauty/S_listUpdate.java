package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.R;

import java.util.Calendar;

/*
 * Created by jhj0104 on 2016-11-17.
 */

public class S_listUpdate extends Activity {

    TextClock clk;
    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list_update);

        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("data");
        TextView etUpdateTitle = (TextView)findViewById(R.id.editUpdateTitle);
        TextView etUpdatePeriod= (TextView) findViewById(R.id.editUpdatePeriodDate);
        etUpdateTitle.setText(String.valueOf(data.Title));
        etUpdatePeriod.setText(String.valueOf(data.Date));

    }
    public void onClick_updateList (View view){
        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("data");
        final String TextTitle = data.Title;
        final String TextDate = data.Date;

        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        String etUpdateTitle = ((EditText)findViewById(R.id.editUpdateTitle)).getText().toString();
        String etUpdateContent = ((EditText) findViewById(R.id.editUpdateContent)).getText().toString();
        String etUpdatePeriodDate = ((EditText)findViewById(R.id.editUpdatePeriodDate)).getText().toString();
        String etUpdatePeriodTime = ((EditText)findViewById(R.id.editUpdatePeriodTime)).getText().toString();

        if(etUpdateTitle.isEmpty()){
            Toast.makeText(getBaseContext(), "제목...제목을 입력해주세요...ㅜ_ㅜ ", Toast.LENGTH_SHORT).show();
        }
        else{
            dbHelper.delete_DL(TextDate, TextTitle);
            dbHelper.insert_DL(etUpdatePeriodDate,etUpdatePeriodTime,etUpdateTitle,etUpdateContent, "false");

            Toast.makeText(getBaseContext(), "내용이 수정되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    public void onClick_btnTrashCan (View view){
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("data");
        final String TextTitle = data.Title;
        final String TextDate = data.Date;

        dbHelper.delete_DL(TextDate, TextTitle);
        Toast.makeText(getBaseContext(), "내용이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
        finish();
    }
}
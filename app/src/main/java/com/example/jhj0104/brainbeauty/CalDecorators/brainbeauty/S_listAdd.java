package com.example.jhj0104.brainbeauty.CalDecorators.brainbeauty;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by jhj0104 on 2016-11-16.
 */

public class S_listAdd extends Activity{

    TextClock clk;
    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();
    String adjustDate= (new SimpleDateFormat("yyyy.MM.dd").format(date));
    String adjustTime = (new SimpleDateFormat("HH:mm").format(date));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list_add);
    }

    public void onClick_saveList(View view){
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        String etTitle = ((EditText)findViewById(R.id.editTitle)).getText().toString();
        String etContent = ((EditText) findViewById(R.id.editContent)).getText().toString();

        if(etTitle.isEmpty()){
            Toast.makeText(getBaseContext(), "제목을 입력해주세요. ", Toast.LENGTH_SHORT).show();
        }
        else{
            dbHelper.insert_DL(adjustDate,adjustTime,etTitle,etContent, "false");
            Toast.makeText(getBaseContext(), "새로운 할 일이 추가되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}

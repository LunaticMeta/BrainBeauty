package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.example.jhj0104.brainbeauty.R.menu.d_list_save;

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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(d_list_save, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.toString()) {
            case "d_list_save_btn" :

                String weekDay = "";
                Calendar c = Calendar.getInstance();
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                if (Calendar.MONDAY == dayOfWeek)           weekDay = "월";
                else if (Calendar.TUESDAY == dayOfWeek)     weekDay = "화";
                else if (Calendar.WEDNESDAY == dayOfWeek)   weekDay = "수";
                else if (Calendar.THURSDAY == dayOfWeek)    weekDay = "목";
                else if (Calendar.FRIDAY == dayOfWeek)      weekDay = "금";
                else if (Calendar.SATURDAY == dayOfWeek)    weekDay = "토";
                else if (Calendar.SUNDAY == dayOfWeek)      weekDay = "일";


                String etTitle = ((EditText)findViewById(R.id.editTitle)).getText().toString();
                String etContent = ((EditText) findViewById(R.id.editContent)).getText().toString();
                String etWeather = ((EditText) findViewById(R.id.editWeather)).getText().toString();

                if(etContent.isEmpty()){
                    Toast.makeText(getBaseContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    DBHelper dbHelper = new DBHelper(getApplicationContext(), "DIARY_DB", 1);
                    DBHelper dbHelper2 = new DBHelper(getApplicationContext(), "DIARY_CHECK_DB", 1);
                    int writeNum = dbHelper2.DC_Date_check(adjustDate + ".");
                    if (writeNum >= 0) {
                        writeNum += 1;
                        dbHelper2.update_DC_WRITE_NUM(adjustDate + ".", String.valueOf(writeNum));
                        Toast.makeText(getBaseContext(), adjustDate + "." + " " + writeNum, Toast.LENGTH_SHORT).show();
                    } else dbHelper2.insert_DC(adjustDate + ".", "1");

                    dbHelper.insert_DI(adjustDate + ".", adjustTime, etTitle, etContent, etWeather, weekDay, "false");
                    Toast.makeText(getBaseContext(), "새로운 일기가 추가되었습니다.", Toast.LENGTH_SHORT).show();

                    Intent myIntent = new Intent(D_diaryAdd.this, D_diaryList.class);
                    D_diaryAdd.this.startActivity(myIntent);
                    finish();
                }
                break;
        }
        return true;
    }
}

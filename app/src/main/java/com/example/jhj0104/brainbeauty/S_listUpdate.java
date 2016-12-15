package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.util.Calendar;

import static com.example.jhj0104.brainbeauty.R.menu.s_list_update;
/*
 * Created by jhj0104 on 2016-11-17.
 */

public class S_listUpdate extends AppCompatActivity {

    TextClock clk;
    Calendar calendar = Calendar.getInstance();
    java.util.Date date = calendar.getTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list_update);

        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("Date");
        TextView etUpdateTitle = (TextView)findViewById(R.id.editUpdateTitle);
        TextView etUpdateContent =  (TextView)findViewById(R.id.editUpdateContent);
        TextView etUpdatePeriod= (TextView) findViewById(R.id.editUpdatePeriodDate);

        etUpdateTitle.setText(String.valueOf(data.Title));
        etUpdateContent.setText(String.valueOf(data.Content));
        etUpdatePeriod.setText(String.valueOf(data.Date));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(s_list_update, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("Date");
        String TextTitle = data.Title;
        String TextDate = data.Date;

        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        String etUpdateTitle = ((EditText)findViewById(R.id.editUpdateTitle)).getText().toString();
        String etUpdateContent = ((EditText) findViewById(R.id.editUpdateContent)).getText().toString();
        String etUpdatePeriodDate = ((EditText)findViewById(R.id.editUpdatePeriodDate)).getText().toString();

        switch (item.toString()) {
            case "s_list_update_btn" :

                //String etUpdatePeriodTime = ((EditText)findViewById(R.id.editUpdatePeriodTime)).getText().toString();

                if(etUpdateTitle.isEmpty()){
                    Toast.makeText(getBaseContext(), "제목을 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    dbHelper.delete_DL(TextDate, TextTitle);
                    //dbHelper.insert_DL(etUpdatePeriodDate,etUpdatePeriodTime,etUpdateTitle,etUpdateContent, "false");
                    dbHelper.insert_DL(etUpdatePeriodDate,"",etUpdateTitle,etUpdateContent, "false");

                    Toast.makeText(getBaseContext(), "내용이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                    data = new S_data(TextDate, TextTitle);
                    intent = new Intent(getApplicationContext(), S_main.class);
                    intent.putExtra("Date",data);
                    startActivity(intent);
                    finish();
                }
                break;

            case "s_list_delete_btn":
                dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
                TextTitle = data.Title;
                TextDate = data.Date;

                dbHelper.delete_DL(TextDate, TextTitle);
                Toast.makeText(getBaseContext(), "내용이 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                data = new S_data(TextDate, TextTitle);
                intent = new Intent(getApplicationContext(), S_main.class);
                intent.putExtra("Date",data);
                startActivity(intent);
                finish();
                break;
        }
        return true;
    }
}
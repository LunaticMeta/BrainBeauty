package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jhj0104.brainbeauty.DB.DBHelper;

/**
 * Created by jhj0104 on 2016-11-21.
 */

public class D_diaryUpdate extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_diary_update);

        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
        Intent intent = getIntent();
        S_data diary = (S_data) intent.getSerializableExtra("Diary");
        TextView etUpdateTitle = (TextView)findViewById(R.id.editUpdateDTitle);
        TextView etUpdateContent =  (TextView)findViewById(R.id.editUpdateDContent);
        TextView etUpdateWeather= (TextView) findViewById(R.id.editUpdateDWeather);

        etUpdateTitle.setText(String.valueOf(diary.Title));
        etUpdateContent.setText(String.valueOf(diary.Content));
        etUpdateWeather.setText(String.valueOf(diary.Weather));

    }
    public void onClick_updateDiary (View view){
        Intent intent = getIntent();
        S_data diary = (S_data) intent.getSerializableExtra("Diary");
        String createTitle = String.valueOf(diary.Title);
        String createDate = String.valueOf(diary.Date);

        String etUpdateTitle = ((EditText)findViewById(R.id.editUpdateDTitle)).getText().toString();
        String etUpdateContent = ((EditText) findViewById(R.id.editUpdateDContent)).getText().toString();
        String etUpdateWeather = ((EditText)findViewById(R.id.editUpdateDWeather)).getText().toString();
        if(etUpdateContent.toString().isEmpty()){
            Toast.makeText(getBaseContext(), "내용을 입력해주세요.", Toast.LENGTH_SHORT).show();
        }
        else{
            DBHelper dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
            dbHelper.update_DI_LIST(createTitle, createDate, etUpdateTitle, etUpdateWeather, etUpdateContent);

            Toast.makeText(getBaseContext(), "일기가 수정되었습니다.", Toast.LENGTH_SHORT).show();
            Intent myIntent = new Intent(D_diaryUpdate.this, D_diaryList.class);
            D_diaryUpdate.this.startActivity(myIntent);
            finish();
        }
    }
}

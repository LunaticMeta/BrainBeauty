package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import static com.example.jhj0104.brainbeauty.R.id.diaryDate;
import static com.example.jhj0104.brainbeauty.R.id.diaryTitle;
import static com.example.jhj0104.brainbeauty.R.layout.activity_d_my_diary;
import static java.lang.String.valueOf;

/**
 * Created by jhj0104 on 2016-11-29.
 */

public class D_myDiary extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_d_my_diary);

        TextView diary_date = (TextView) findViewById(diaryDate);
        TextView diary_title = (TextView) findViewById(diaryTitle);
        TextView diary_weather = (TextView) findViewById(R.id.diaryWeather);
        TextView diary_content = (TextView) findViewById(R.id.diaryContent);

        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("Diary");
        final String myDate = valueOf(data.Date);
        diary_date.setText("날짜: "+myDate);

        SpannableString content = new SpannableString(valueOf(data.Title));
        content.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        diary_title.setText(content);

        SpannableString content2 = new SpannableString(valueOf(data.Weather));
        content2.setSpan(new UnderlineSpan(), 0, content2.length(), 0);
        diary_weather.setText("날씨: "+content2);

        SpannableString content3 = new SpannableString(valueOf(data.Content));
        content3.setSpan(new UnderlineSpan(), 0, content3.length(), 0);
        diary_content.setText(content3);
    }
}

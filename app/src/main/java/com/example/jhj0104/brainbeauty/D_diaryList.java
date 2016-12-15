package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daimajia.swipe.util.Attributes;
import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.util.ArrayList;

import static com.example.jhj0104.brainbeauty.R.layout.activity_d_diary_list;
import static com.example.jhj0104.brainbeauty.R.menu.d_list_add;

/**
 * Created by jhj0104 on 2016-11-22.
 */

public class D_diaryList extends AppCompatActivity{
    DBHelper dbHelper;

    /*
        Calendar cal = Calendar.getInstance();
	     cal.set(2016, a-1,b);
	     int day = cal.get(Calendar.DAY_OF_WEEK)+1;
	     return Day[day%7];
    */



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_d_diary_list);

        final GridView gridView = (GridView) findViewById(R.id.d_diary_gridView);
        final GridViewAdapter adapter = new GridViewAdapter(this);

        adapter.setMode(Attributes.Mode.Multiple);
        gridView.setAdapter(adapter);
        gridView.setSelected(true);


        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemLongClick", "onItemLongClick:" + position);
                return false;
            }
        });

        //클릭했을 때
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemClick", "onItemClick:" + position);

                dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
                ArrayList<String> dateArray = dbHelper.get_DI_Date();
                ArrayList<String> titleArray = dbHelper.get_DI_Title();
                ArrayList<String> contentArray = dbHelper.get_DI_Content();
                ArrayList<String> weatherArray = dbHelper.get_DI_Weather();
                String[] date =dateArray.toArray(new String[0]);
                String[] title =titleArray.toArray(new String[0]);
                String[] content =contentArray.toArray(new String[0]);
                String[] weather =weatherArray.toArray(new String[0]);

                S_data data = new S_data(date[position], title[position], content[position], weather[position]);
                Intent intent = new Intent(getApplicationContext(),D_myDiary.class);
                intent.putExtra("Diary",data);
                startActivity(intent);

            }
        });


        gridView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("onItemSelected", "onItemSelected:" + position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(d_list_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.toString()) {
            case "d_list_add_btn" :
                Intent myIntent = new Intent(D_diaryList.this, D_diaryAdd.class);
                D_diaryList.this.startActivity(myIntent);
                finish();
                break;
        }
        return true;
    }
}
package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.daimajia.swipe.util.Attributes;
import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.util.ArrayList;

import static com.example.jhj0104.brainbeauty.R.layout.activity_d_diary_list;

/**
 * Created by jhj0104 on 2016-11-22.
 */

public class D_diaryList extends AppCompatActivity{
    DBHelper dbHelper;

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

    /*public void onClick_heart (View view){

        ImageView heart = (ImageView) view.findViewById(R.id.heart);
        heart.setImageResource(R.drawable.heart);
    }

    public void onClick_eraser(View view){
        dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
        ArrayList<String> titleArray = dbHelper.get_DI_Title();
        String[] title =titleArray.toArray(new String[0]);

        TextView tv= (TextView) findViewById(position);
        String p = tv.getText().toString();
        int a = Integer.parseInt(p);

        S_data data = new S_data("", title[a-1]);
        Intent intent = new Intent(getApplicationContext(),D_diaryUpdate.class);
        intent.putExtra("Diary",data);
        startActivity(intent);
        finish();
    }

    public void onClick_garbage(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure?")
                .setMessage("일기를 정말로 삭제하시겠습니까?")
                .setCancelable(false)
                .setPositiveButton("예", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
                        ArrayList<String> titleArray = dbHelper.get_DI_Title();
                        String[] title =titleArray.toArray(new String[0]);

                        TextView tv= (TextView) findViewById(position);
                        String p = tv.getText().toString();
                        int a = Integer.parseInt(p);
                        dbHelper.delete_DI_TITLE(title[a-1]);
                        Toast.makeText(getBaseContext(), "일기가 삭제되었습니다.", Toast.LENGTH_SHORT).show();

                        Intent myIntent = new Intent(D_diaryList.this, D_diaryList.class);
                        D_diaryList.this.startActivity(myIntent);
                        finish();
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
    }*/

}
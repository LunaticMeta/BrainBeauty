package com.example.jhj0104.brainbeauty;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.widget.Toast.makeText;

public class S_main extends AppCompatActivity {

    private ArrayList<String> work1s = new ArrayList<>();
    private ArrayList<String> work2s = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();
    Date today = calendar.getTime();
    Date tomorrow = calendar.getTime();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list);

        final TextView txdate = (TextView) findViewById(R.id.textDate);
        Intent intent = getIntent();
        final S_data data = (S_data) intent.getSerializableExtra("Date");
        txdate.setText(String.valueOf(data.Date));
        final String myDate = String.valueOf(data.Date);

        DateFormat sdFormat = new SimpleDateFormat("yyyy.MM.dd.");
        try {
            Date tempDate = sdFormat.parse(myDate);
            today = tempDate;
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> DL_List = dbHelper.get_DL_LIST(myDate);
        String[] ListTitle = new String[DL_List.get(0).size()];
        String[] ListBool = new String[DL_List.get(0).size()];
        String[] ListCreateDate = new String[DL_List.get(0).size()];

        for(int i=0; i<DL_List.get(0).size(); i++){

            ListTitle[i] = DL_List.get(0).get(i);
            ListBool[i] = DL_List.get(1).get(i);
            ListCreateDate[i] = DL_List.get(2).get(i);

            if(ListBool[i].equals("false")){
                work1s.add(new String(DL_List.get(0).get(i)));
            }
            else{
                work2s.add(new String(DL_List.get(0).get(i)));
            }
        }

        //create our new array adapter
        final ArrayAdapter<String> adapter1 = new Work1Adapter(this, 0, work1s);
        final ArrayAdapter<String> adapter2 = new Work2Adapter(this, 0, work2s);

        //Find list view and bind it with the custom adapter
        final ListView listView1 = (ListView) findViewById(R.id.s_list1);
        listView1.setAdapter(adapter1);
        final ListView listView2 = (ListView) findViewById(R.id.s_list2);
        listView2.setAdapter(adapter2);

        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(listView1, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(int position) {
                return true;
            }
            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    dbHelper.update_DL_FLAG(myDate.toString(),adapter1.getItem(position),"true");
                    adapter2.add(adapter1.getItem(position));
                    adapter1.remove(adapter1.getItem(position));

                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String str = work1s.get(position);
                String a = str + " 선택";
                makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();

                S_data data = new S_data(myDate.toString(), str);
                Intent intent = new Intent(getApplicationContext(), S_listUpdate.class);
                intent.putExtra("Date",data);
                startActivity(intent);
            }
        });
        listView1.setOnTouchListener(touchListener);
        listView1.setOnScrollListener(touchListener.makeScrollListener());


        SwipeDismissListViewTouchListener touchListener2 = new SwipeDismissListViewTouchListener(listView2, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            @Override
            public boolean canDismiss(int position) {
                return true;
            }
            @Override
            public void onDismiss(ListView listView2, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    dbHelper.update_DL_FLAG(myDate.toString(),adapter2.getItem(position),"false");
                    adapter1.add(adapter2.getItem(position));
                    adapter2.remove(adapter2.getItem(position));

                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            }
        });
        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String str = work2s.get(position);
                String a = str + " 선택";
                makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();

                S_data data = new S_data(myDate.toString(), str);
                Intent intent = new Intent(getApplicationContext(), S_listUpdate.class);
                intent.putExtra("Date",data);
                startActivity(intent);
            }
        });
        listView2.setOnTouchListener(touchListener2);
        listView2.setOnScrollListener(touchListener2.makeScrollListener());
    }


    public void onClick_back(View view){
        calendar.setTime(today);
        calendar.add(calendar.DAY_OF_MONTH,-1);
        java.util.Date tomorrowDate = calendar.getTime();
        String tomorrow= (new SimpleDateFormat("yyyy.MM.dd").format(tomorrowDate));

        S_data data = new S_data(tomorrow+".", "");
        Intent intent = new Intent(getApplicationContext(), S_main.class);
        intent.putExtra("Date",data);
        startActivity(intent);
        finish();
    }
    public void onClick_next(View view){
        calendar.setTime(today);
        calendar.add(calendar.DAY_OF_MONTH,1);
        java.util.Date tomorrowDate = calendar.getTime();
        String tomorrow= (new SimpleDateFormat("yyyy.MM.dd").format(tomorrowDate));

        S_data data = new S_data(tomorrow+".", "");
        Intent intent = new Intent(getApplicationContext(), S_main.class);
        intent.putExtra("Date",data);
        startActivity(intent);
        finish();

    }
    public void onClick_btnCalendar(View view){
        Intent myIntent = new Intent(S_main.this, S_calendar.class);
        S_main.this.startActivity(myIntent);
        finish();

    }
    public void onClick_add(View view){
        S_data data = new S_data(today.toString(), "");
        Intent intent = new Intent(getApplicationContext(), S_listAdd.class);
        intent.putExtra("Date",data);
        startActivity(intent);
    }
    public void onClick_calendar(View view){

    }
    @Override
    public void onBackPressed(){
        Intent myIntent = new Intent(S_main.this, BB_menu.class);
        S_main.this.startActivity(myIntent);
        finish();
    }

    protected void onResume(){ //다시 정상적으로 ㅅ ㅣㄹ행될 때
        super.onResume();
    }
}

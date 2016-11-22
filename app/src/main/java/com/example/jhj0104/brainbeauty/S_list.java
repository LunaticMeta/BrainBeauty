package com.example.jhj0104.brainbeauty;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.layout.simple_list_item_1;
import static android.R.layout.simple_list_item_2;
import static android.widget.Toast.makeText;
import static com.example.jhj0104.brainbeauty.R.id.listView_done;

public class S_list extends Activity {

    TextView myDate;
    ListView mListView1;
    ListView mListView2;
    ArrayAdapter<String> mAdapter1;
    ArrayAdapter<String> mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list);
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);

        final TextView txdate = (TextView) findViewById(R.id.textDate);
        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("Date");
        txdate.setText(String.valueOf(data.Date));
        final String myDate = String.valueOf(data.Date);

        ArrayList<ArrayList<String>> DL_List = dbHelper.get_DL_LIST(myDate);

        final String[] ListTitle = new String[DL_List.get(0).size()];
        String[] ListBool = new String[DL_List.get(0).size()];
        String[] ListCreateDate = new String[DL_List.get(0).size()];
        final ArrayList<String> Doit_done = new ArrayList<>();
        final ArrayList<String> Doit_yet = new ArrayList<>();

        for(int i=0; i<DL_List.get(0).size(); i++){

            ListTitle[i] = DL_List.get(0).get(i);
            ListBool[i] = DL_List.get(1).get(i);
            ListCreateDate[i] = DL_List.get(2).get(i);

            if(ListBool[i].equals("false")){
                Doit_yet.add(ListTitle[i]);
            }
            else Doit_done.add(DL_List.get(0).get(i));
        }

        mListView1 = (ListView)findViewById(R.id.listView_yet);
        mAdapter1 = new ArrayAdapter<String>(this, simple_list_item_1, android.R.id.text1, Doit_yet);
        mListView1.setAdapter(mAdapter1);

        mListView2 = (ListView)findViewById(listView_done);
        mAdapter2 = new ArrayAdapter<String>(this, simple_list_item_2, android.R.id.text2, Doit_done);
        mListView2.setAdapter(mAdapter2);

        //------------------------------↓↓ ListView_yet ↓↓------------------------------//
        SwipeDismissListViewTouchListener touchListener = new SwipeDismissListViewTouchListener(mListView1, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);

            @Override
            public boolean canDismiss(int position) {
                return true;
            }

            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    String title_name =mAdapter1.getItem(position);
                    mAdapter2.add(mAdapter1.getItem(position));
                    mAdapter1.remove(mAdapter1.getItem(position));
                    dbHelper.update_DL_FLAG(myDate,title_name,"true");

                    mAdapter1.notifyDataSetChanged();
                    mAdapter2.notifyDataSetChanged();
                }

            }
        });
        mListView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String str = Doit_yet.get(position);
                String a = str + " 선택";
                makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();

                S_data data = new S_data(myDate.toString(), str);
                Intent intent = new Intent(getApplicationContext(), S_listUpdate.class);
                intent.putExtra("Date",data);
                startActivity(intent);
            }
        });
        mListView1.setOnTouchListener(touchListener);
        mListView1.setOnScrollListener(touchListener.makeScrollListener());

        //------------------------------↓↓ ListView_done ↓↓------------------------------//
        SwipeDismissListViewTouchListener touchListener2 = new SwipeDismissListViewTouchListener(mListView2, new SwipeDismissListViewTouchListener.DismissCallbacks() {
            DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);

            @Override
            public boolean canDismiss(int position) {
                return true;
            }

            @Override
            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                for (int position : reverseSortedPositions) {
                    String title_name =mAdapter2.getItem(position);
                    mAdapter1.add(mAdapter2.getItem(position));
                    mAdapter2.remove(mAdapter2.getItem(position));
                    dbHelper.update_DL_FLAG(myDate.toString(),title_name,"false");

                    mAdapter1.notifyDataSetChanged();
                    mAdapter2.notifyDataSetChanged();
                }
            }
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView text = (TextView) view;
                text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            }
        });
        mListView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                String str = Doit_done.get(position);
                String a = str + " 선택";
                makeText(getApplicationContext(), a, Toast.LENGTH_SHORT).show();

                S_data data = new S_data(myDate.toString(), str);
                Intent intent = new Intent(getApplicationContext(), S_listUpdate.class);
                intent.putExtra("Date",data);
                startActivity(intent);
            }
        });
        mListView2.setOnTouchListener(touchListener2);
        mListView2.setOnScrollListener(touchListener2.makeScrollListener());
    }

    public void onClick_btnReturn(View view){
        finish();
    }

    public void onClick_back(View view){

    }
    public void onClick_next(View view){

    }
    public void onClick_btnCalendar(View view){
        Intent myIntent = new Intent(S_list.this, S_calendar.class);
        S_list.this.startActivity(myIntent);
        finish();
    }
    public void onClick_add(View view){
        Intent myIntent = new Intent(S_list.this, S_listAdd.class);
        S_list.this.startActivity(myIntent);
    }
    public void onClick_calendar(View view){
    }

    protected void onResume(){ //다시 정상적으로 ㅅ ㅣㄹ행될 때
        super.onResume();
    }


}

package com.example.jhj0104.brainbeauty;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class S_list extends Activity {

    ListView mListView1;
    ListView mListView2;
    ArrayList<S_workData> mList1;
    ArrayList<S_workedData> mList2;
    S_workYetAdapter mAdapter1;
    S_workDoneAdapter mAdapter2;
    ArrayAdapter<S_workYetAdapter> myArrAdapter1;
    ArrayAdapter<S_workDoneAdapter> myArrAdapter2;
    S_workData sWorkData;
    S_workedData sWorkedData;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_list);
        DBHelper dbHelper = new DBHelper(getApplicationContext(),"DO_LIST_DB",1);

        Intent intent = getIntent();
        S_data data = (S_data) intent.getSerializableExtra("Date");
        final String myDate = String.valueOf(data.Date);
        final TextView txdate = (TextView) findViewById(R.id.textDate);
        txdate.setText(String.valueOf(data.Date));

        ArrayList<ArrayList<String>> DL_List = dbHelper.get_DL_LIST(myDate);
        final String[][] ListData = new String[3][DL_List.get(0).size()];
        final String[] ListTitle = new String[DL_List.get(0).size()];
        String[] ListBool = new String[DL_List.get(0).size()];
        String[] ListCreateDate = new String[DL_List.get(0).size()];
        mList1 = new ArrayList<S_workData>();
        mList2 = new ArrayList<>();

        for(int i=0; i<DL_List.get(0).size(); i++){
            ListTitle[i] = DL_List.get(0).get(i);
            ListBool[i] = DL_List.get(1).get(i);
            ListCreateDate[i] = DL_List.get(2).get(i);
            if(i==1) ListBool[i] ="true"; //test code!!!

            if(ListBool[i].equals("false")){
                sWorkData = new S_workData(ListTitle[i]);
                mList1.add(sWorkData);
                mAdapter1 = new S_workYetAdapter(getApplicationContext(),R.layout.activity_s_list_yet, mList1);
                myArrAdapter1 = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_1, mList1); //ArrayList<S_workData>
                //myArrAdapter1 = new ArrayAdapter(this,simple_list_item_1, mList1); //ArrayList<S_workData>
            }
            else{
                sWorkedData = new S_workedData(ListTitle[i]);
                mList2.add(sWorkedData);
                mAdapter2 = new S_workDoneAdapter(getApplicationContext(),R.layout.activity_s_list_done, mList2);
            }
        }
        //
        mListView1 = (ListView) findViewById(R.id.listView_yet);
        mListView1.setAdapter(mAdapter1);
        mListView2 = (ListView)findViewById(R.id.listView_done);
        mListView2.setAdapter(mAdapter2);



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

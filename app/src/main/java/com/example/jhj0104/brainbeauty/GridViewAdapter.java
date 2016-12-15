package com.example.jhj0104.brainbeauty;

/*
 * Created by jhj0104 on 2016-11-22.
 */


import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.BaseSwipeAdapter;
import com.example.jhj0104.brainbeauty.DB.DBHelper;

import java.util.ArrayList;
import java.util.Calendar;

import static com.example.jhj0104.brainbeauty.R.layout.grid_item;

public class GridViewAdapter extends BaseSwipeAdapter {
    DBHelper dbHelper;
    DBHelper dbHelper2;
    private Context mContext;


    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.d_diary_swipe;
    }

    @Override
    public View generateView(final int position, ViewGroup parent) {
        View view =  LayoutInflater.from(mContext).inflate(grid_item, null);

        final SwipeLayout swipeLayout = (SwipeLayout)view.findViewById(getSwipeLayoutResourceId(position));
        swipeLayout.setShowMode(SwipeLayout.ShowMode.LayDown);

//        swipeLayout.findViewById(R.id.heart).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                Toast.makeText(mContext, "my position : " + String.valueOf(position), Toast.LENGTH_SHORT).show();
//                ImageView heart = (ImageView) view.findViewById(R.id.heart);
//                heart.setImageResource(R.drawable.heart);
//            }
//        });

        swipeLayout.findViewById(R.id.eraser).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbHelper = new DBHelper(mContext,"DIARY_DB",1);
                ArrayList<String> dateArray = dbHelper.get_DI_Date();
                ArrayList<String> titleArray = dbHelper.get_DI_Title();
                ArrayList<String> contentArray = dbHelper.get_DI_Content();
                ArrayList<String> weatherArray = dbHelper.get_DI_Weather();
                String[] date =dateArray.toArray(new String[0]);
                String[] title =titleArray.toArray(new String[0]);
                String[] content =contentArray.toArray(new String[0]);
                String[] weather =weatherArray.toArray(new String[0]);

                int a = position;
                S_data data = new S_data(date[a], title[a], content[a], weather[a]);
                Intent intent = new Intent(mContext,D_diaryUpdate.class);
                intent.putExtra("Diary",data);

                mContext.startActivity(intent);
                ((Activity)mContext).finish();
            }

        });

        swipeLayout.findViewById(R.id.garbage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Are you sure?")
                        .setMessage("일기를 정말로 삭제하시겠습니까?")
                        .setCancelable(false)
                        .setPositiveButton("예", new DialogInterface.OnClickListener(){
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                dbHelper = new DBHelper(mContext,"DIARY_DB",1);
                                dbHelper2 = new DBHelper(mContext,"DIARY_CHECK_DB",1);

                                ArrayList<String> titleArray = dbHelper.get_DI_Title();
                                ArrayList<String> dateArray = dbHelper.get_DI_Date();
                                String[] date =dateArray.toArray(new String[0]);
                                String[] title =titleArray.toArray(new String[0]);

                                int writeNum = dbHelper2.DC_Date_check(date[position]);
                                if(writeNum>0) writeNum-=1;
                                dbHelper2.update_DC_WRITE_NUM(date[position], String.valueOf(writeNum));
                                dbHelper.delete_DI_TITLE(title[position]);

                                Toast.makeText(mContext, "일기가 삭제되었습니다.\n"+date[position]+" "+writeNum, Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
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
            }
        });
        return view;
    }

    @Override
    public void fillValues(int position, View convertView) {
        dbHelper = new DBHelper(mContext.getApplicationContext(),"DIARY_DB",1);

        String weekDay = "";
        Calendar c = Calendar.getInstance();

        TextView t = (TextView)convertView.findViewById(R.id.position);
        TextView tt = (TextView)convertView.findViewById(R.id.position2);
        TextView ttt = (TextView)convertView.findViewById(R.id.position_id);
        TextView diaryTitle = (TextView)convertView.findViewById(R.id.diaryTitle);
        TextView diaryContent = (TextView)convertView.findViewById(R.id.diaryContent);

        ArrayList<String> dateArray = dbHelper.get_DI_Date();
        ArrayList<String> titleArray = dbHelper.get_DI_Title();
        ArrayList<String> contentArray = dbHelper.get_DI_Content();
        ArrayList<String> weekDayArray = dbHelper.get_DI_WeekDay();

        String[] date =dateArray.toArray(new String[0]);
        String[] title =titleArray.toArray(new String[0]);
        String[] content =contentArray.toArray(new String[0]);
        String[] weekday =weekDayArray.toArray(new String[0]);

        t.setText(title[position]);
        String t2 = applyNewLineCharacter('T', t);
        t.setText(content[position]);
        String t3 = applyNewLineCharacter('C', t);

        ttt.setText(date[position]);
        tt.setText(weekday[position]);
        t.setText(date[position].substring(8,10));
        //ttt.setText(Integer.toString(position + 1));


        diaryTitle.setText(t2);
        diaryContent.setText(t3);
    }

    //http://blog.naver.com/jolangma/150117004035
    private String applyNewLineCharacter(char mode, TextView textView)
    {
        Paint paint = textView.getPaint();
        String text = (String) textView.getText();
        // Count line of TextView
        int lines = 1;
        int maxLine = 3;
        int frameWidth = 1000;
        if(mode=='T') {maxLine=2; frameWidth = 750;}
        int startIndex = 0;
        int endIndex = paint.breakText(text , true, frameWidth, null);
        String save = text.substring(startIndex, endIndex);

        while(true)
        {
            startIndex = endIndex;
            text = text.substring(startIndex);

            if(text.length() == 0) break;
            else lines++;

            if(lines == maxLine) // 3줄이 넘으면 줄임표(...)를 붙인다.
            {
                save = save.substring(0, save.length() - 2) + "...";
                break;
            }

            endIndex = paint.breakText(text, true, frameWidth, null);
            save += "\n" + text.substring(0, endIndex);
        }
        return (save);
    }

    @Override
    public int getCount() {

        dbHelper = new DBHelper(mContext.getApplicationContext(),"DIARY_DB",1);
        int count = dbHelper.get_DI_count();
        dbHelper.close();
        return count;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

}

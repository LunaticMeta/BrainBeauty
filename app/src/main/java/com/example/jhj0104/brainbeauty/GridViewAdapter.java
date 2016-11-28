package com.example.jhj0104.brainbeauty;

/*
 * Created by jhj0104 on 2016-11-22.
 */

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.ArrayList;

public class GridViewAdapter extends BaseSwipeAdapter {
    //DBHelper dbHelper = new DBHelper(getApplicationContext(),"DIARY_DB",1);
    DBHelper dbHelper;
    private Context mContext;

    public GridViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.d_diary_swipe;
    }

    @Override
    public View generateView(int position, ViewGroup parent) {
        return LayoutInflater.from(mContext).inflate(R.layout.grid_item, null);


    }

    @Override
    public void fillValues(int position, View convertView) {
        dbHelper = new DBHelper(mContext.getApplicationContext(),"DIARY_DB",1);

        TextView t = (TextView)convertView.findViewById(R.id.position);
        TextView diaryTitle = (TextView)convertView.findViewById(R.id.diaryTitle);

        ArrayList<String> titleArray = dbHelper.get_DI_Title();
        String[] title =titleArray.toArray(new String[0]);

        t.setText(title[position]);
        String t2 = applyNewLineCharacter(t);
        t.setText((position + 1 )+".");
        diaryTitle.setText(t2);
    }

    //http://blog.naver.com/jolangma/150117004035
    private String applyNewLineCharacter(TextView textView)
    {
        Paint paint = textView.getPaint();
        String text = (String) textView.getText();
        int frameWidth = 370;
        int startIndex = 0;
        int endIndex = paint.breakText(text , true, frameWidth, null);
        String save = text.substring(startIndex, endIndex);
        // Count line of TextView
        int lines = 1;

        while(true)
        {
            // Set new start index
            startIndex = endIndex;
            // Get substring the remaining of text
            text = text.substring(startIndex);

            if(text.length() == 0) break;
            else lines++;

            if(lines == 4) // 3줄이 넘으면 줄임표(...)를 붙인다.
            {
                save = save.substring(0, save.length() - 2) + "...";
                break;
            }

            // Calculate end of index that fits
            endIndex = paint.breakText(text, true, frameWidth, null);
            // Append substring that fits into the frame
            save += "\n" + text.substring(0, endIndex);
        }
        // Set text to TextView
        return save;
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

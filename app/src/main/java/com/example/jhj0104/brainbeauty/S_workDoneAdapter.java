package com.example.jhj0104.brainbeauty;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jhj0104 on 2016-11-23.
 */

public class S_workDoneAdapter extends BaseAdapter {

    private Context mContext = null;
    private int layout = 0;
    private ArrayList<S_workedData> h_data = null;
    private LayoutInflater inflater = null;

    public S_workDoneAdapter(Context c, int layout, ArrayList<S_workedData> d) {
        this.mContext = c;
        this.layout = layout;
        this.h_data = d;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if(convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView S_list_doneTxt = (TextView) convertView.findViewById(R.id.work_text2);
        S_list_doneTxt.setText(h_data.get(position).text);
        S_list_doneTxt.setPaintFlags(S_list_doneTxt.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
        return convertView;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return h_data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return h_data;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }
}

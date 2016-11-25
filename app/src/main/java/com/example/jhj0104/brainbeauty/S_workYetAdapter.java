package com.example.jhj0104.brainbeauty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by jhj0104 on 2016-11-23.
 */

public class S_workYetAdapter extends BaseAdapter{
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<S_workData> w_data = null;
    private LayoutInflater inflater = null;

    public S_workYetAdapter(Context c, int layout, ArrayList<S_workData> d) {
        this.mContext = c;
        this.layout = layout;
        this.w_data = d;
        this.inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        if (convertView == null) {
            convertView = inflater.inflate(this.layout, parent, false);
        }
        TextView S_list_yetTxt = (TextView) convertView.findViewById(R.id.work_text1);
        S_list_yetTxt.setText(w_data.get(position).text);
        return convertView;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return w_data.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        //return w_data;
        return 1;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

}

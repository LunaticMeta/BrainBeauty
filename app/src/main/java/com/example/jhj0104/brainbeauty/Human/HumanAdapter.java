package com.example.jhj0104.brainbeauty.Human;

/**
 * Created by jhj0104 on 2016-11-23.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jhj0104.brainbeauty.R;

import java.util.ArrayList;

public class HumanAdapter extends BaseAdapter {
    private Context mContext = null;
    private int layout = 0;
    private ArrayList<Human> h_data = null;
    private LayoutInflater inflater = null;

    public HumanAdapter(Context c, int layout, ArrayList<Human> d) {
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
        ImageView Hu_image = (ImageView) convertView.findViewById(R.id.Human_image);
        TextView Hu_name = (TextView) convertView.findViewById(R.id.Human_name);
        TextView Hu_gender = (TextView) convertView.findViewById(R.id.Human_gender);


        Hu_image.setImageBitmap(h_data.get(position).image);
        Hu_name.setText(h_data.get(position).name);
        Hu_gender.setText(h_data.get(position).gender);

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

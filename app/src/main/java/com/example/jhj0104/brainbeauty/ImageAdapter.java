package com.example.jhj0104.brainbeauty;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

/**
 * Created by jhj0104 on 2016-11-22.
 */
public class ImageAdapter implements ListAdapter {
    private Context mContext;
    private Integer[] mThumbIds = {
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart,
            R.drawable.heart, R.drawable.heart
    };

    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    public int getCount(){
        return mThumbIds.length;
    }
    public Object getItem(int position){
        return null;
    }
    public long getItemId(int position){
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        ImageView imageView;
        if(convertView == null){
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else{
            imageView = (ImageView) convertView;
        }

        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    @Override
    public int getItemViewType(int i) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int i) {
        return false;
    }
}

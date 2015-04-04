package com.jgeeks.traveltime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Vlad on 03.04.2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    public final int[] Imageid;
    public ImageAdapter(Context c,int[] Imageid ) {
        mContext = c;
        this.Imageid = Imageid;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Imageid.length;
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        grid = new View(mContext);
        grid = inflater.inflate(R.layout.grid_single, null);
        if (convertView == null) {
            ImageView imageView = (ImageView)grid.findViewById(R.id.grid_image);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setImageResource(Imageid[position]);
        } else {
            grid = (View) convertView;
        }
        return grid;
    }
}
package com.jgeeks.traveltime;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Vlad on 03.04.2015.
 */
public class ImageAdapter extends BaseAdapter {

    private static String path = "TravelTime";
    int[] Imageid = {
            R.drawable.sample_0,
            R.drawable.sample_1,
            R.drawable.sample_2,
            R.drawable.sample_3,
            R.drawable.sample_4,
            R.drawable.sample_5,
            R.drawable.sample_6,
            R.drawable.sample_7,
    };

    private Context mContext;
    public ImageAdapter(Context c) {
        mContext = c;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return Imageid.length;
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return this.Imageid[position];
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    ArrayList<String> f = new ArrayList<String>();   // list of available files in  path
    File[] listFile;

    public void getSdcardImages()
    {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), path);

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                f.add(listFile[i].getAbsolutePath());

            }
        }
        else{
            Log.e("t", "Else");
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        View grid;
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ImageView imageView;

        grid = inflater.inflate(R.layout.grid_single, null);

        if (convertView == null) {
            imageView = (ImageView)grid.findViewById(R.id.grid_image);
        }
        else {
            imageView = (ImageView) convertView;
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        if(f.size() != 0) {
            Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
            imageView.setImageBitmap(myBitmap);
        }
        else{
            //imageView.setImageResource(Imageid[position]);
        }
        return imageView;
    }
}
package com.jgeeks.traveltime;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Vlad on 03.04.2015.
 */
public class ImageAdapter extends BaseAdapter {

    private static String path = "TravelTime";

    private Context mContext;
    public ImageAdapter(Context c) {
        mContext = c;
        getSdcardImages();
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return listFile.length;
    }
    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return listFile[position];
    }
    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public static ArrayList<String> f = new ArrayList<String>();   // list of available files in  path
    public File[] listFile;

    public void getSdcardImages()
    {
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), path);

        if (file.isDirectory())
        {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++)
            {

                try {
                    f.add(listFile[i].getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
        else{
            Log.e("t", "Else");
        }
    }

    public static void setPic(ImageView mImageView, String mCurrentPhotoPath) {
        // Get the dimensions of the View
        int targetW = 800;
        int targetH = 600;

        // Get the dimensions of the bitmap
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        // Decode the image file into a Bitmap sized to fill the View
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        if(mCurrentPhotoPath.contains("mp4")){
            mImageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(mCurrentPhotoPath, MediaStore.Video.Thumbnails.FULL_SCREEN_KIND));
        }
        else{
            Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
            mImageView.setImageBitmap(bitmap);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
            setPic(imageView,f.get(position));
            //Bitmap myBitmap = BitmapFactory.decodeFile(f.get(position));
            //imageView.setImageBitmap(myBitmap);
        }
        return imageView;
    }
}
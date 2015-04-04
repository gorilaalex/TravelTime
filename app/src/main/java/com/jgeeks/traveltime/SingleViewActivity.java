package com.jgeeks.traveltime;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import java.util.ArrayList;


public class SingleViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view);

        // Get intent data
        Intent i = getIntent();

        // Selected image id
        int position = i.getExtras().getInt("id");
        ArrayList<String> paths = i.getExtras().getStringArrayList("files");

        ImageAdapter imageAdapter = new ImageAdapter(this);

        String file = (String) paths.get(position);
        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        ImageAdapter.setPic(imageView,file);
    }

}

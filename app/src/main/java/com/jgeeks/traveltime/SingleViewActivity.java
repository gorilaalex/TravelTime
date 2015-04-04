package com.jgeeks.traveltime;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;


public class SingleViewActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#04BFBF")));
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.color1));

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

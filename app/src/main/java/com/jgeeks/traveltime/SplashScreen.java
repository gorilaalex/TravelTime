package com.jgeeks.traveltime;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ImageView;


public class SplashScreen extends Activity {
    private final int SPLASH_DISPLAY_LENGTH = 50000;
    private boolean ping = false;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActionBar actionBar = getActionBar();

        if (actionBar != null) {
            actionBar.hide();
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayUseLogoEnabled(false);
        }
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.splashImage);

        startAnotherActivity();

    }

    private void getCount() throws InterruptedException {
        Thread.sleep(5000);
        ping = true;
    }

    private void startAnotherActivity(){
        Intent intent = new Intent(this,TimelineActivity.class);

        startActivity(intent);

    }

}

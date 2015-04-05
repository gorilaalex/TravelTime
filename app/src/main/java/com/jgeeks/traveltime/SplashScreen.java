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

import com.jgeeks.traveltime.db.TripReaderHelper;
import com.jgeeks.traveltime.model.Trip;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreen extends Activity {
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

        Timer waitTimer = new Timer();
        waitTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                //interstitialCanceled = true;
                SplashScreen.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // The interstitial didn't load in a reasonable amount of time. Stop waiting for the
                        // interstitial, and start the application.
                        startAnotherActivity();
                    }
                });
            }
        }, 3000);

    }

    private void initializeDb(){

    }

    private void startAnotherActivity(){
        Intent intent = new Intent(this,TimelineActivity.class);

        startActivity(intent);
        finish();

    }

}

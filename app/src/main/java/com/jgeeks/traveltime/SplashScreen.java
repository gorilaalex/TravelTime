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

import java.util.List;


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

        TripReaderHelper db = new TripReaderHelper(this);
//        db.addTrip(new Trip("Vacanta1", "pathul meu", "", ""));
  //      db.addTrip(new Trip("Vacanta3", "path ", "", ""));

        //List<Trip> l = db.getAllTrips();

        //Trip t = db.getTrip("Vacanta3");
          startAnotherActivity();
    }

    private void initializeDb(){

    }

    private void startAnotherActivity(){
        Intent intent = new Intent(this,TimelineActivity.class);

        startActivity(intent);
        finish();

    }

}

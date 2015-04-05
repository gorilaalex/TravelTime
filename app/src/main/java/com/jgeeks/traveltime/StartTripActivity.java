package com.jgeeks.traveltime;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.jgeeks.traveltime.db.TripReaderHelper;
import com.jgeeks.traveltime.model.Trip;

import java.util.Date;


public class StartTripActivity extends Activity {

    private EditText mEditTitle;
    private EditText mEditLocation;
    private Trip trip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_trip);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#04BFBF")));
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.color1));

        mEditTitle = (EditText) findViewById(R.id.editTitle);
        mEditLocation = (EditText) findViewById(R.id.editLocation);
    }

    public void addTrip(View view){
        TripReaderHelper db = new TripReaderHelper(this);
        trip = new Trip();

        if(mEditTitle.getText().toString()!=null) {
            trip.setTitle(mEditTitle.getText().toString());
            trip.setPath(mEditLocation.getText().toString());
            trip.setStartDate(new Date());
            trip.setEndDate(new Date());
            try {
                db.addTrip(trip);
            }
            catch(CursorIndexOutOfBoundsException e){
                e.printStackTrace();

            }
            Intent intent = new Intent(this, GalleryActivity.class);
            if(trip!=null) {
                intent.putExtra("TripName", trip.getTitle());
            }
            else intent.putExtra("TripName", "string");

            startActivity(intent);
        }
        else Toast.makeText(this,"Title null",Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start_trip, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

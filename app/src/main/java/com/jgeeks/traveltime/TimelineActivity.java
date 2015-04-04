package com.jgeeks.traveltime;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.jgeeks.traveltime.db.TripReaderHelper;
import com.jgeeks.traveltime.model.Trip;

import java.util.LinkedList;
import java.util.List;


public class TimelineActivity extends Activity {

    private String tripName="";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;List<Trip> l=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#04BFBF")));
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(this.getResources().getColor(R.color.color1));

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        getTrips();
        // specify an adapter (see also next example)
        mAdapter = new TimelineAdapter(l);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new TimelineItemClickListener(this, mRecyclerView, new TimelineItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                Toast.makeText(getApplicationContext(),"On item " + position + " clicked",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position)
            {
                Toast.makeText(getApplicationContext(),"On item " + position + " long click",Toast.LENGTH_SHORT).show();
            }
        }));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
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
            Intent intent = new Intent(this, GalleryActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_trip) {

            Intent intent = new Intent(this, StartTripActivity.class);
            startActivity(intent);

            if(l!=null){

                //l.add(0,new Trip("vacanta test","pathul meu","nulllable","nullable"));
                //mRecyclerView.addItemDecoration();
                //openDialog1();
                //mAdapter.notifyDataSetChanged();
            }


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openDialog1() {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.timeline_additem);
        dialog.setTitle("Add a new trip");


        // set the custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.txtName);
        text.setText("Android custom dialog example!");
        //ImageView image = (ImageView) dialog.findViewById(R.id.image);
       // image.setImageResource(R.drawable.ic_launcher);

       // Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        // if button is clicked, close the custom dialog
       /* dialogButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });*/

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        //do nothing

    }

    private void getTrips(){
        List<Trip> trlst;

        TripReaderHelper db = new TripReaderHelper(this);
        trlst = db.getAllTrips();
        if(l==null){
            l = new LinkedList<>();
        }
        for(int i=trlst.size()-1;i>=0;i--) {
            l.add(trlst.get(i));
        }
    }
}

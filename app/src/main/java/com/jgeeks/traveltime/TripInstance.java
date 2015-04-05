package com.jgeeks.traveltime;

import android.content.Context;

import com.jgeeks.traveltime.model.Trip;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alexgaluska on 04/04/15.
 */
public class TripInstance {
    public List<Trip> trips;
    public File[] listFile;
    public static TripInstance mInstance;
    public Context mContext;

    public static TripInstance newInstance(Context context){

        if(mInstance==null){
            mInstance = new TripInstance(context);
        }
        return mInstance;
    };

    private TripInstance(Context context){
        mContext = context;
        trips = new LinkedList<>();

    }

    public List<Trip> getTrips(){
        return null;
    }
}

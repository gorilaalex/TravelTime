package com.jgeeks.traveltime.model;

import java.util.Date;

/**
 * Created by alexgaluska on 04/04/15.
 */
public class Trip {
    private int id;
    private String title;
    private String path;
    private Date startDate;
    private Date endDate;

    public Trip(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public String getPath(){
        return this.path;
    }

    public String getTitle(){
        return this.title;
    }

    private Date convertDate(String stringDate){
        return new Date();
    }
}

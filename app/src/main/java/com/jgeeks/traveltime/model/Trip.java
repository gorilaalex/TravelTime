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

    public Trip() {}
    public Trip(String t,String p,String st,String ed){
        this.title = t;
        this.path = p;
    }

    public void setId(int i) {
        this.id = i;
    }
    public void setTitle(String t){
        this.title = t;
    }
    public void setPath(String p) {
        this.path = p;
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

    public Date getEndDate() {return this.endDate;}

    public Date getStartDate() { return this.startDate;}

    private Date convertDate(String stringDate){
        return new Date();
    }
}

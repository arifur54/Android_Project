package com.quickonference.quickonference.conference;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.*;
import com.google.gson.*;

public class Conference {
    private int conferenceID = 0;
    private String name, address, date, time, details;
    List<String> sponsors;
    List<String> attendees;

    public Conference (String name, String address, String date, String time, String details)  {
        this.name = name;
        this.address = address;
        this.date = date;
        this.time = time;
        this.details = details;
        sponsors = new ArrayList<String>();
        attendees = new ArrayList<String>();
    }

    public String getName () {return this.name; }
    public String getAddress () {
        return this.address;
    }
    public String getDate () {
        return this.date;
    }
    public String getTime () {
        return this.time;
    }
    public String getDetails () {return this.details;}
    public boolean addSponsor (String sponsorName) {
        return this.sponsors.add(sponsorName);
    }
    public boolean addattendee (String attendeeName) {
        return this.attendees.add(attendeeName);
    }
    public static void storeConference (Conference con, Activity activity) {
        Gson gson = new Gson();
        String conferenceJson = gson.toJson(con);
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("conferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref_conference.edit();
        editor.putString(con.name, conferenceJson);
        editor.apply();
    }
    public static void addToSchedule (Conference con, Activity activity) {
        Gson gson = new Gson();
        String conferenceJson = gson.toJson(con);
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("my_conferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpref_conference.edit();
        editor.putString(con.name, conferenceJson);
        editor.apply();
    }
    public static Conference getConferenceFromJson (String json, Activity activity) {
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("conferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Conference conference = gson.fromJson(json, Conference.class);
        return conference;
    }
    public static Conference getMyConferenceFromJson (String json, Activity activity) {
        SharedPreferences sharedpref_conference = activity.getSharedPreferences("my_conferences", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        Conference conference = gson.fromJson(json, Conference.class);
        return conference;
    }
    @Override
    public String toString() {
        return "Name: " + this.name + "\n" +
               "Address: "+ this.address + "\n" +
               "Details: " + this.details + "\n" +
               "Date and Time: " + this.date + " " + this.time + "\n\n";
    }
}

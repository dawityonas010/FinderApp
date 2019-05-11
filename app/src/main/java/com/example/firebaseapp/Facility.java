package com.example.firebaseapp;

import com.google.firebase.database.IgnoreExtraProperties;

import java.util.Map;

@IgnoreExtraProperties
public class Facility {
    public String name;
    public String type;
    public String description;
    public String location;
    public Map<String,String> geo_location;
    public int verified_true_by;
    public int verified_false_by;

    public Facility() {

    }
    public Facility(String name, String type,String location,String description,
                    Map<String,String> geo_location,int verified_true_by,int verified_false_by){
        this.name=name;
        this.type=type;
        this.location=location;
        this.description=description;
        this.geo_location=geo_location;
        this.verified_true_by=verified_true_by;
        this.verified_false_by=verified_false_by;
    }
}

package com.example.shutter_firebase.data;


import com.google.firebase.firestore.DocumentReference;

import java.io.Serializable;

public class User implements Serializable{

    private String uid;

    private String name;
    private String country;
    private String email;

    private String city;

    private String aboutme;
    private String imageurl;


    public User(
            String uid,
            String name,
            String email,
            String country,
            String city,
            String aboutme,
            String imageurl
    )

    {
        this.uid = uid;
        this.name = name;
        this.country = country;
        this.city = city;
        this.aboutme = aboutme;
        this.email = email;
        this.imageurl = imageurl;
    }

    public String getId(){
        return uid;
    }
    public String getName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getCountry(){
        return country;
    }
    public String getCity(){
        return city;
    }
    public String getAboutMe(){
        return aboutme;
    }
    public String getImageUrl(){
        return imageurl;
    }


}
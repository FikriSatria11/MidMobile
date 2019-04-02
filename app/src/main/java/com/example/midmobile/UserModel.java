package com.example.midmobile;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class UserModel extends RealmObject {
    @Required
    @PrimaryKey
    private String id;  //no.hp
    @Required
    private String name;
    @Required
    private String password;
    private boolean isMale;
    private String address;
    private String ttl_place;
    private String ttl_date;
    private String ttl_month;
    private String ttl_year;
    private String religion;
    private String email;
    //attributes

    public UserModel () {}

    public UserModel (String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getId () {
        return this.id;
    }

    public void setId (String id) {
        this.id = id;
    }

    public String getName () {
        return this.name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public String getPassword () {
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public boolean getGender () {
        return this.isMale;
    }

    public void setGender (boolean isMale) {
        this.isMale = isMale;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTtl_place() {
        return ttl_place;
    }

    public void setTtl_place(String ttl_place) {
        this.ttl_place = ttl_place;
    }

    public String getTtl_date() {
        return ttl_date;
    }

    public void setTtl_date(String ttl_date) {
        this.ttl_date = ttl_date;
    }

    public String getTtl_month() {
        return ttl_month;
    }

    public void setTtl_month(String tel_month) {
        this.ttl_month = tel_month;
    }

    public String getTtl_year() {
        return ttl_year;
    }

    public void setTtl_year(String ttl_year) {
        this.ttl_year = ttl_year;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


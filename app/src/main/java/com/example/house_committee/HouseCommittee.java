package com.example.house_committee;

import java.io.Serializable;

public class HouseCommittee implements Serializable {
    private String fname;
    private String lname;
    private String id;
    private String password;
    private String Years_of_seniority;
    private String email;

    public HouseCommittee(String fname, String lname, String id, String password, String years_of_seniority, String email) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.password = password;
        Years_of_seniority = years_of_seniority;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYears_of_seniority() {
        return Years_of_seniority;
    }

    public void setYears_of_seniority(String years_of_seniority) {
        Years_of_seniority = years_of_seniority;
    }
}

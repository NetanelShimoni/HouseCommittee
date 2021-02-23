package com.example.house_committee;

import java.io.Serializable;

public class HouseCommittee implements Serializable {
    private String Fname;
    private String Lname;
    private String id;
    private String password;
    private String Years_of_seniority;
    private String email;


    public HouseCommittee(String fname, String lname, String id, String password, String years_of_seniority, String email) {
        this.Fname = fname;
        this.Lname = lname;
        this.id = id;
        this.password = password;
        this.Years_of_seniority = years_of_seniority;
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
        return Fname;
    }

    public void setFname(String fname) {
        Fname = fname;
    }

    public String getLname() {
        return Lname;
    }

    public void setLname(String lname) {
        Lname = lname;
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

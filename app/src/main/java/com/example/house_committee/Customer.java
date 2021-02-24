package com.example.house_committee;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String fname;
    private String lname;
    private String id;
    private String email;
    private String password;
    private String  apartment_number;
    private ArrayList  <Integer> monthly_amount;

    public Customer(){

    }



    public Customer(String fname, String lname, String id, String email, String password, String apartment_number) {
        this.fname = fname;
        this.lname = lname;
        this.id = id;
        this.email = email;
        this.password = password;
        this.apartment_number = apartment_number;
        this.monthly_amount = new ArrayList<Integer>();
        for (int i = 0; i <12 ; i++) {
            this.getMonthly_amount().add(0);

        }
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return "Customer{" +
                "Fname='" + fname + '\'' +
                ", Lname='" + lname + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", Apartment_number='" + apartment_number + '\'' +
                ", Monthly_amount=" + monthly_amount +
                '}';
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
        fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        lname = lname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApartment_number() {
        return apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        apartment_number = apartment_number;
    }

    public ArrayList<Integer> getMonthly_amount() {
        return monthly_amount;
    }

    public void setMonthly_amount(ArrayList<Integer> monthly_amount) {
        monthly_amount = monthly_amount;
    }
}

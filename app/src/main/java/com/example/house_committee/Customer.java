package com.example.house_committee;

import java.io.Serializable;
import java.util.ArrayList;

public class Customer implements Serializable {
    private String Fname;
    private String Lname;
    private String id;
    private String email;
    private String  Apartment_number;
    private ArrayList  <Integer> Monthly_amount;

    public Customer(String fname, String lname, String id, String email, String apartment_number) {
      this.Fname = fname;
        this.Lname = lname;
        this.id = id;
        this.email = email;
        this.Apartment_number = apartment_number;
        this.Monthly_amount  =  new ArrayList();
        for (int i = 0; i <12 ; i++) {
           this.Monthly_amount.add(6);
        }
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Fname='" + Fname + '\'' +
                ", Lname='" + Lname + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", Apartment_number='" + Apartment_number + '\'' +
                ", Monthly_amount=" + Monthly_amount +
                '}';
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

    public String getApartment_number() {
        return Apartment_number;
    }

    public void setApartment_number(String apartment_number) {
        Apartment_number = apartment_number;
    }

    public ArrayList<Integer> getMonthly_amount() {
        return Monthly_amount;
    }

    public void setMonthly_amount(ArrayList<Integer> monthly_amount) {
        Monthly_amount = monthly_amount;
    }
}

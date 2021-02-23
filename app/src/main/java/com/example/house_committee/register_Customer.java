package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_Customer extends AppCompatActivity {
    private EditText edFname , edLname, edPassword , edEmail, edNamApartment,edId;
    private String Fname , Lname, Password , Email,NamApartment,id;
    private Customer customer;
    private Button join;
    DatabaseReference DbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__customer);
        edFname= findViewById(R.id.et_C_Fname);
        edLname= findViewById(R.id.et_C_Lname);
        join = findViewById(R.id.button_register_C);
        edPassword= findViewById(R.id.et_C_Password);
        edId= findViewById(R.id.et_C_Id);
        edEmail= findViewById(R.id.et_C_mail);
        edNamApartment= findViewById(R.id.et_C_num_Aparmment);



        // init firebase
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DbRef = mDatabase.getInstance().getReference("Customer");

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fname=edFname.getText().toString();
                Lname=edLname.getText().toString();
                Password=edPassword.getText().toString();
                Email=edEmail.getText().toString();
                NamApartment=edNamApartment.getText().toString();
                id=edId.getText().toString();
                String id_s = DbRef.push().getKey();
                customer = new Customer(Fname,Lname,id,Email,NamApartment); // get the HouseCommittee
                DbRef.child(id_s).setValue(customer);// insert the HouseCommittee on firebase
                Intent i = new Intent(register_Customer.this, Login.class);
                startActivity(i);
            }
        });

    }
}
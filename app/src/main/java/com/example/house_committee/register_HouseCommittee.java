package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register_HouseCommittee extends AppCompatActivity {
    private EditText edFname , edLname, edPassword , edEmail,edYours,edId;
    private String Fname , Lname, Password , Email,Yours,id;
    private HouseCommittee houseCommittee;
    private Button join;
    DatabaseReference DbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register__house_committee);
        edFname= findViewById(R.id.ed_name);
        edLname= findViewById(R.id.ed_name2);
        join = findViewById(R.id.button_registerIn);
        edPassword= findViewById(R.id.etPassword);
        edId= findViewById(R.id.ed_d);
        edEmail= findViewById(R.id.ed_email);
        edYours= findViewById(R.id.ed_exprience);



        // init firebase
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DbRef = mDatabase.getInstance().getReference("HouseCommittee");

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fname=edFname.getText().toString();
                Lname=edLname.getText().toString();
                Password=edPassword.getText().toString();
                Email=edEmail.getText().toString();
                Yours=edYours.getText().toString();
                id=edId.getText().toString();
                String id_s = DbRef.push().getKey();
                houseCommittee = new HouseCommittee(Fname,Lname,id,Password,Yours,Email); // get the HouseCommittee
                DbRef.child(id).setValue(houseCommittee);// insert the HouseCommittee on firebase
                Intent i = new Intent(register_HouseCommittee.this, Login.class);
                startActivity(i);
            }
        });


    }
}
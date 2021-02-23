package com.example.house_committee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Login extends AppCompatActivity {
    private String email , password;
    private Button btlogin;
    private Button btregister;
    private EditText ED_email,ED_password;
    private DatabaseReference CustomerDbRef;
    private DatabaseReference HouseDbRef;
    private boolean flag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ED_email= findViewById(R.id.email);
        ED_password= findViewById(R.id.password);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        CustomerDbRef = mDatabase.getInstance().getReference("Customer");
        HouseDbRef = mDatabase.getInstance().getReference("HouseCommittee");
        btlogin = findViewById(R.id.btConnect);
        btregister = findViewById(R.id.bt_Register);
        Intent i = getIntent();
        this.flag = i.getBooleanExtra("who_is_it",false);

        btregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag==true) {
                    Intent intent = new Intent(Login.this, register_HouseCommittee.class);
                    intent.putExtra("who_is_it", flag);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(Login.this, register_Customer.class);
                    intent.putExtra("who_is_it", flag);
                    startActivity(intent);
                }
            }
        });
//Checks if a logged in user is a tenant (Customer)
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ED_email.getText().toString();
                password = ED_password.getText().toString();
            CustomerDbRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for (DataSnapshot ds : snapshot.getChildren()) {
                        if (ds.child("email").getValue().equals(email) && ds.child("password").getValue().equals(password)) //Verify the data
                        {
                            Object object = ds.getValue(Object.class); // parsing to object Customer
                            String json = new Gson().toJson(object);
                            Customer user = new Gson().fromJson(json, Customer.class);
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
            }
        });

        //Checks if a logged in user is a house committee
        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = ED_email.getText().toString();
                password = ED_password.getText().toString();
                HouseDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("email").getValue().equals(email) && ds.child("password").getValue().equals(password)) //Verify the data
                            {
                                Object object = ds.getValue(Object.class); // parsing to object HouseCommittee
                                String json = new Gson().toJson(object);
                                HouseCommittee houseCommitteeuser = new Gson().fromJson(json, HouseCommittee.class);
                                Toast.makeText(Login.this, "OK!!!", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(Login.this, HouseCommittee_Home.class);
                                i.putExtra("name", houseCommitteeuser);
                                startActivity(i);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });

    }




}
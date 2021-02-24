package com.example.house_committee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Change_Password extends AppCompatActivity {
    private EditText ed_email , ed_newpass;
    private Button ok;
    private String email, newPassword;
    private DatabaseReference CustomerDbRef;
    private DatabaseReference HouseDbRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change__password);
        ed_email=findViewById(R.id.ed_email_for_passwoerd);
        ed_newpass=findViewById(R.id.et_new_password);

        // init firebase
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        CustomerDbRef = mDatabase.getInstance().getReference("Customer");
        HouseDbRef = mDatabase.getInstance().getReference("HouseCommittee");
        ok = findViewById(R.id.button_change);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=ed_email.getText().toString();
                newPassword=ed_newpass.getText().toString();
                CustomerDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("email").getValue().equals(email)) //Verify the data
                            {
                                Object object = ds.getValue(Object.class); // parsing to object Customer
                                String json = new Gson().toJson(object);
                                Customer user = new Gson().fromJson(json, Customer.class);
                                CustomerDbRef.child(user.getId()).child("password").setValue(newPassword);
                                Toast.makeText(Change_Password.this, "Password is changed", Toast.LENGTH_LONG).show();


                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                HouseDbRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            if (ds.child("email").getValue().equals(email)) //Verify the data
                            {
                                Object object = ds.getValue(Object.class); // parsing to object Customer
                                String json = new Gson().toJson(object);
                                HouseCommittee houseCommittee = new Gson().fromJson(json, HouseCommittee.class);
                                HouseDbRef.child(houseCommittee.getId()).child("password").setValue(newPassword);
                                Toast.makeText(Change_Password.this, "Password is changed", Toast.LENGTH_LONG).show();



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
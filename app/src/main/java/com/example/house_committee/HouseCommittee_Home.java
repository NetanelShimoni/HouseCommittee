package com.example.house_committee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import javax.xml.transform.sax.SAXSource;

public class HouseCommittee_Home extends AppCompatActivity {
    private Button bt_Details_of_tenant_payments;
    private Button bt_All_payments_in_the_building;
    private DatabaseReference customer_db;
    String ans = "";
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_committee__home);
        bt_Details_of_tenant_payments = findViewById(R.id.bt_Details_of_tenant_payments);
        bt_All_payments_in_the_building = findViewById(R.id.bt_All_payments_in_the_building);

        bt_Details_of_tenant_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HouseCommittee_Home.this, Details_of_tenant_payments.class);
                startActivity(i);
            }
        });
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        customer_db = mDatabase.getInstance().getReference("Customer");

        bt_All_payments_in_the_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    customer_db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for (int i = 0; i < 12; i++) {
                                num = i + 1;
                                System.out.println("num==="+num);
                                ans+=num;
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Object object = ds.getValue(Object.class);
                                    String json = new Gson().toJson(object);
                                    System.out.println("json=" + json);
                                    Customer t = new Gson().fromJson(json, Customer.class);
                                    System.out.println("t=" + t.toString() + "t.get ap" + t.getApartment_number() + "num=" + num);

                                    if (t.getApartment_number() != null && t.getApartment_number().equals(num)) {

                                        ans += t.getMonthly_amount().toString();



                                    }
                                }


                            }
                            System.out.println("ans end===" + ans);
                            Intent i2 = new Intent(HouseCommittee_Home.this, All_payments_in_the_building.class);
                            i2.putExtra("all_details",ans);
                            startActivity(i2);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


            }

        });
    }
}
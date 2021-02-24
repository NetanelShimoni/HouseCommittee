package com.example.house_committee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

import javax.xml.transform.sax.SAXSource;

public class HouseCommittee_Home extends AppCompatActivity {
    private Button bt_Details_of_tenant_payments;
    private Button bt_All_payments_in_the_building;
    private Button bt_Update_tenant_payments;
    private Button bt_Introduce_monthly_building_income;
    private TextView welcome;
    private DatabaseReference customer_db;
   private String ans = "";
    int num=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        HouseCommittee houseCommittee = (HouseCommittee) i.getSerializableExtra("name");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_committee__home);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        customer_db = mDatabase.getInstance().getReference("Customer"); // init firebase
        bt_Details_of_tenant_payments = findViewById(R.id.bt_Details_of_tenant_payments);
        welcome = findViewById(R.id.textView_welcome);
        welcome.setText("Welcome back "+houseCommittee.getFname() +" "+ houseCommittee.getLname());
        bt_All_payments_in_the_building = findViewById(R.id.bt_All_payments_in_the_building);
        bt_Update_tenant_payments = findViewById(R.id.bt_Update_tenant_payments);
        bt_Introduce_monthly_building_income = findViewById(R.id.bt_Introduce_monthly_building_income);

        bt_Introduce_monthly_building_income.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                customer_db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList <Customer> customerArrayList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Object object = ds.getValue(Object.class);
                            String json = new Gson().toJson(object);
                            System.out.println("json=" + json);
                            Customer t = new Gson().fromJson(json, Customer.class);
                            customerArrayList.add(t);

                        }
                        int amount[] = new int[12]; // all payment per month
                        for (int k = 0; k <customerArrayList.size() ; k++) { //
                            Customer temp = customerArrayList.get(k);
                            for (int i = 0; i <12 ; i++) {
                                amount[i]+=temp.getMonthly_amount().get(i);
                            }
                        }
                        for (int i = 0; i <12 ; i++) {
                            ans+=String.valueOf(i+1)+" "+amount[i]+"\n";

                        }
                        Intent i2 = new Intent(HouseCommittee_Home.this, Introduce_monthly_building_income.class);
                        i2.putExtra("all_details",ans); // pass answer thr next page for show on the screen
                        startActivity(i2);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

        });

        bt_Update_tenant_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HouseCommittee_Home.this, Update_tenant_payments.class);
                startActivity(i);
            }
        });

        bt_Details_of_tenant_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HouseCommittee_Home.this, Details_of_tenant_payments.class);
                startActivity(i);
            }
        });


        bt_All_payments_in_the_building.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    customer_db.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            ans ="";
                            ArrayList <Customer> customerArrayList = new ArrayList<>();
                                for (DataSnapshot ds : snapshot.getChildren()) {
                                    Object object = ds.getValue(Object.class);
                                    String json = new Gson().toJson(object);
                                    Customer t = new Gson().fromJson(json, Customer.class);
                                    customerArrayList.add(t);
                                    }


                            for (int i = 0; i <customerArrayList.size() ; i++) {
                                ans+= customerArrayList.get(i).getApartment_number()+ " ";
                                for (int j = 0; j <12 ; j++) {
                                    if(customerArrayList.get(i).getMonthly_amount().get(j)!=0){
                                        ans+=String.valueOf(j+1);
                                    }

                                }
                                ans+="\n";

                            }


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
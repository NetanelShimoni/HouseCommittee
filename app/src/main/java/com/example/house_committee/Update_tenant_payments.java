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

import java.util.ArrayList;

public class Update_tenant_payments extends AppCompatActivity {
    private Button add;
    private EditText et_num_apartment, et_month,et_money;
    private String num_apartment, month,money;
    private DatabaseReference customer_db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_tenant_payments);
        add = findViewById(R.id.bt_pyment);
        et_money = findViewById(R.id.et_numMoney);
        et_month = findViewById(R.id.et_numMon);
        et_num_apartment = findViewById(R.id.et_numApartmrrnt);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        customer_db = mDatabase.getInstance().getReference("Customer");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            num_apartment= et_num_apartment.getText().toString();
            month=et_month.getText().toString();
            money=et_money.getText().toString();
                System.out.println("mont is: "+ money + " month is: "+month +"num apartment "+num_apartment );
                customer_db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        ArrayList<Customer> customerArrayList = new ArrayList<>();
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Object object = ds.getValue(Object.class);
                            String json = new Gson().toJson(object);
                            System.out.println("json=" + json);
                            Customer t = new Gson().fromJson(json, Customer.class);
                            customerArrayList.add(t);

                        }
                        int index_of_for=-1;
                        for (int i = 0; i <customerArrayList.size() ; i++) {
                            if(customerArrayList.get(i).getApartment_number().equals(num_apartment)){
                                index_of_for= i;
                            }
                        }
                        int index = Integer.parseInt(month);
                        index=index-1;
                        int monney = Integer.parseInt(money);
                        if(index_of_for!=-1) {
                            customerArrayList.get(index_of_for).getMonthly_amount().set(index, monney);
                          //  customer_db.child(customerArrayList.get(index_of_for).getId()).removeValue();
                            customer_db.child(customerArrayList.get(index_of_for).getId()).setValue(customerArrayList.get(index_of_for));
                           // customer_db.child(customerArrayList.get(index_of_for).getId()).setValue(customerArrayList.get(index_of_for));
                            Toast.makeText(Update_tenant_payments.this, "Insert Payment", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(Update_tenant_payments.this,HouseCommittee_Home.class);
                            startActivity(i);
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
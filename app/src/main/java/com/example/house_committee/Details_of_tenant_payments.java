package com.example.house_committee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

public class Details_of_tenant_payments extends AppCompatActivity {
    private EditText num_of_apartment;
    private Button enter;
    private String num;
    private TextView show_payment;
    private DatabaseReference customer_db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_tenant_payments);
        num_of_apartment= findViewById(R.id.ed_numforchack);
        enter = findViewById(R.id.button_forchack);
        show_payment = findViewById(R.id.textView_forshow);
        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        customer_db = mDatabase.getInstance().getReference("Customer");
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                num = num_of_apartment.getText().toString();
                customer_db.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String ans="";
                        for (DataSnapshot ds : snapshot.getChildren()) {
                            Object object = ds.getValue(Object.class);
                            String json = new Gson().toJson(object);
                            Customer t= new Gson().fromJson(json, Customer.class);
                            String num_apa = t.getApartment_number();
                            System.out.println(t.toString());
                            System.out.println("i am in!! num is:"+  num_apa);
                            System.out.println("i am in!! num is in username type:"+ num);
                            if(num_apa!=null && num_apa.equals(num)){
                                for (int i = 0; i <12 ; i++) {
                                    if(t.getMonthly_amount().get(i)!=0){
                                        ans+=(i+1) +" ";
                                    }
                                }
                                System.out.println("ans is: "+ans);
                                show_payment.setText(ans);
                                break;

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
package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HouseCommittee_Home extends AppCompatActivity {
    private Button bt_Details_of_tenant_payments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_committee__home);
        bt_Details_of_tenant_payments= findViewById(R.id.bt_Details_of_tenant_payments);
        bt_Details_of_tenant_payments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HouseCommittee_Home.this, Details_of_tenant_payments.class);
                startActivity(i);
            }
        });
    }
}
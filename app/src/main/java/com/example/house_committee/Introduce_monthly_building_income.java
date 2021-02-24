package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Introduce_monthly_building_income extends AppCompatActivity {
    private TextView textViewpay;
    private TextView all_payments;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        String ans =i.getStringExtra("all_details");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_monthly_building_income);

        textViewpay = findViewById(R.id.tv_allpay);
        all_payments = findViewById(R.id.all_payments_sum);

        all_payments.setText(ans);

    }
}
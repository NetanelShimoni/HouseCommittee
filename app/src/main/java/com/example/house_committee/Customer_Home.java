package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Customer_Home extends AppCompatActivity {
    private TextView details;
    private String ans="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer__home);
        details =findViewById(R.id.textView_detaiels);
        Intent i = getIntent();

        Customer c = (Customer) i.getSerializableExtra("name");
        for (int j = 0; j <c.getMonthly_amount().size() ; j++) {
            if(c.getMonthly_amount().get(j)!=0){
                ans+=String.valueOf(j+1)+" ";
            }
        }
        details.setText(ans);

    }
}
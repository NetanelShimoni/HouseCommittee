package com.example.house_committee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {
    private Button connect , choose_new_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        connect = findViewById(R.id.connect);
        choose_new_password = findViewById(R.id.choose_new_password);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag ;
                Intent i = getIntent();
                flag= (boolean)i.getBooleanExtra("who_is_it",false);
                Intent intent = new Intent(LoginActivity.this,Login.class);
                intent.putExtra("who_is_it",flag);
                System.out.println("flag is:" +flag);
                startActivity(intent);

            }
        });
    }

}
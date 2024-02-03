package com.hassan.leaverequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import controller.DBHandler;

public class Signup extends AppCompatActivity {
    Button signup_btn ;
    EditText name ;
    EditText password ;
    DBHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_btn = findViewById(R.id.signup_btn);
        name = findViewById(R.id.username_signup);
        password = findViewById(R.id.password_signup);


        db = new DBHandler(this);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.addNewAdmin(name.getText().toString(),password.getText().toString());
                Intent intent = new Intent(Signup.this,Login.class);
                startActivity(intent);
            }
        });
    }
}
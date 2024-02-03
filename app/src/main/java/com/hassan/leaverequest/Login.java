package com.hassan.leaverequest;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputEditText;

import controller.DBHandler;

public class Login extends AppCompatActivity {
    String[] admin_username = {"hassan","hussein","muntadar","admin"};
    String admin_password = "1";
    DBHandler db;
    EditText username ;
    EditText password ;
    Button login ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        db = new DBHandler(this);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.loginBtn);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test","press on login button");
                String userif = username.getText().toString().toLowerCase();
                String passif = password.getText().toString();
                for(String user : admin_username){
                    Log.d("test",user);
                    if (user.equals(userif) && admin_password.equals(passif)){
                        Log.d("test","successful if statement");

                        Intent intent = new Intent(Login.this , AllRequest.class);

                        startActivity(intent);
                    }
                }
                if (db.login(userif,passif)){
                    Log.d("test","successful if statement");
                    Intent intent = new Intent(Login.this , LeaveRequest.class);

                    startActivity(intent);
                }

            }
        });


    }
}
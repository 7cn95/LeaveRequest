package com.hassan.leaverequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import controller.DBHandler;
import model.RequestDB;

public class Detail extends AppCompatActivity {
    DBHandler db;
    Button delete;
    Button accept;
    Button reject;
    TextView name;
    TextView reason;
    TextView date;
    TextView days;
    TextView jt;
    TextView empNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        db = new DBHandler(this);
        delete = findViewById(R.id.deleteBtn);
        accept = findViewById(R.id.accept);
        reject = findViewById(R.id.reject);
        name = findViewById(R.id.deName);
        reason = findViewById(R.id.deReason);
        date = findViewById(R.id.deDate);
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",1);
        RequestDB data = db.getRequestById(id);
        Intent backon = new Intent(Detail.this,AllRequest.class);

        name.setText(data.getEmp_name());
        reason.setText(data.getReason());
        date.setText(data.getLr_date());

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateStatus(String.valueOf(id));
                startActivity(backon);


            }
        });
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.updateStatusR(String.valueOf(id));
                startActivity(backon);

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.delete(id);
                startActivity(backon);
            }
        });


    }
}
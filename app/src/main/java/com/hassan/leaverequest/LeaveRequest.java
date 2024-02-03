package com.hassan.leaverequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import controller.DBHandler;

public class LeaveRequest extends AppCompatActivity {
    DBHandler db;
    EditText name ;
    EditText reason ;
    EditText leaveDate ;
    EditText employeeNum;
    EditText day;
    EditText job_title;

    Button add ;
    Button checkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_request);
        db = new DBHandler(this);
        Log.d("test",db.getDatabaseName());
        add = findViewById(R.id.addNewReqBtm);
        checkBtn = findViewById(R.id.checkBtn);
        name = findViewById(R.id.reqEmpName);
        reason = findViewById(R.id.reqReason);
        leaveDate = findViewById(R.id.reqLeaveRDate);
        day = findViewById(R.id.day);
        employeeNum = findViewById(R.id.employeeNum);
        job_title = findViewById(R.id.job_title);
        Intent getIntent = getIntent();
        String getName = getIntent.getStringExtra("name");

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean cn = name.getText().length()>0;
                    boolean cr = reason.getText().length()>0;
                    boolean cd = leaveDate.getText().length()>0;
                    String inputName = name.getText().toString().toLowerCase();
                    String inputReason = reason.getText().toString().toLowerCase();
                    String inputDate = leaveDate.getText().toString().toLowerCase();
                    int inputDay = Integer.parseInt(day.getText().toString().toLowerCase());
                    String inputEn = employeeNum.getText().toString().toLowerCase();
                    String inputJT = job_title.getText().toString().toLowerCase();
                    Log.d("test","cn "+inputName);
                    Log.d("test","cr "+inputReason);
                    Log.d("test","cd "+inputDate);
                    if (cn && cr && cd) {
                        boolean ex = db.addNewRequest(inputName, inputReason, inputDate,"",inputDay,inputEn,inputJT);
                        Log.d("test",""+ex);
                        if (ex) {
                            Log.d("test", "add new request successfully");
                            Toast.makeText(getApplicationContext(), "تم الطلب بنجاح", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(LeaveRequest.this, CheckReq.class);
                            startActivity(intent);
                        }
                    }
                }
            });
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LeaveRequest.this,CheckReq.class);
                startActivity(intent);
            }
        });

    }
}
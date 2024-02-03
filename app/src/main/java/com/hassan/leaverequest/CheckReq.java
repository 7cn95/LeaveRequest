package com.hassan.leaverequest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;


import java.util.List;

import controller.DBHandler;
import model.RequestDB;

public class CheckReq extends AppCompatActivity {

    DBHandler db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_req);

        db = new DBHandler(this);
        listView = findViewById(R.id.listViewXY);

        List<RequestDB> requests = db.get_all_request();


        CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.list_item, requests);
        listView.setAdapter(adapter);
    }
}
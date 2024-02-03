package com.hassan.leaverequest;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


import java.util.List;

import controller.DBHandler;
import model.RequestDB;

public class AllRequest extends AppCompatActivity {
    DBHandler db;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_request);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        db = new DBHandler(this);
        listView = findViewById(R.id.listViewX);

        List<RequestDB> requests = db.get_all_request();



            CustomArrayAdapter adapter = new CustomArrayAdapter(this, R.layout.list_item, requests);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // تحديد العنصر المحدد
                    RequestDB selectedRequest = (RequestDB) parent.getItemAtPosition(position);

                    // الانتقال إلى صفحة التفاصيل مع تمرير الـ ID
                    Intent detailIntent = new Intent(AllRequest.this, Detail.class);
                    detailIntent.putExtra("id", selectedRequest.getId());
                    startActivity(detailIntent);
                }
            });

    }
}


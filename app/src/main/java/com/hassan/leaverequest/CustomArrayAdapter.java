package com.hassan.leaverequest;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.List;

import model.RequestDB;

public class CustomArrayAdapter extends ArrayAdapter<RequestDB> {

    private static Context context;
    private int resource;

    public CustomArrayAdapter(Context context, int resource, List<RequestDB> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
        }

        TextView employeeNameTextView = convertView.findViewById(R.id.EmployeeName);
        TextView leaveReasonTextView = convertView.findViewById(R.id.LeaveReason);
        TextView leaveDateTextView = convertView.findViewById(R.id.LeaveDate);
        TextView idTextView = convertView.findViewById(R.id.id);  // تحديث هنا
        ImageView statusTextView = convertView.findViewById(R.id.statusLV);


        RequestDB currentItem = getItem(position);
        if (currentItem != null) {
            employeeNameTextView.setText(currentItem.getEmp_name());
            leaveReasonTextView.setText(currentItem.getReason());
            leaveDateTextView.setText(currentItem.getLr_date());
            idTextView.setText(String.valueOf(currentItem.getId()));
            if (currentItem.getStatus().equals("false")){
                statusTextView.setImageResource(R.drawable.statusred);
            }else if (currentItem.getStatus().equals("null")){
                statusTextView.setImageResource(R.drawable.statuswait);
            }
            else if (currentItem.getStatus().equals("true")){
                statusTextView.setImageResource(R.drawable.statusgreen);

            }
        }

        return convertView;
    }
}



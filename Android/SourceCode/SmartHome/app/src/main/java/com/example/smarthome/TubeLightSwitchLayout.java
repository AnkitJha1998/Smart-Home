package com.example.smarthome;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TubeLightSwitchLayout extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList<String> tubelightInfo;
    private final ArrayList<Integer> onOrOff;

    public TubeLightSwitchLayout(Activity context,ArrayList<String> tubelights,ArrayList<Integer> onOrOff)
    {
        super(context,R.layout.list_view_tubelight_switch_layout,tubelights);
        this.context=context;
        this.tubelightInfo=tubelights;
        this.onOrOff=onOrOff;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.list_view_tubelight_switch_layout,null,true);
        Switch sw=rowView.findViewById(R.id.switchOnOffToggle);
        sw.setChecked(onOrOff.get(position)==1?true:false);
        sw.setText(tubelightInfo.get(position));
        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch temp=(Switch)v;
                if(temp.getText().toString().equals("Hall Tubelight"))
                {
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Tubelights/hallTube");
                    if(temp.isChecked()) {
                        ref.setValue(1);
                    }
                    else
                    {
                        ref.setValue(0);
                    }
                }
                else if(temp.getText().toString().equals("Kitchen Tubelight"))
                {
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Tubelights/kitchenTube");
                    if(temp.isChecked()) {
                        ref.setValue(1);
                    }
                    else
                    {
                        ref.setValue(0);
                    }
                }
                else if(temp.getText().toString().equals("Bedroom Tubelight"))
                {
                    DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Tubelights/bedTube");
                    if(temp.isChecked()) {
                        ref.setValue(1);
                    }
                    else
                    {
                        ref.setValue(0);
                    }
                }
            }
        });
        return rowView;


    }
}

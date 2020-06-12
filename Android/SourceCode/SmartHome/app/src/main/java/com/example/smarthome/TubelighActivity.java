package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class TubelighActivity extends AppCompatActivity {

    Users loggedInUser;
    FirebaseDatabase dat;

    ArrayList<Integer> tubelightStatus;
    ArrayList<String> tubelightNames;
    ListView tubeSwitchView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tubeligh);

        Intent recvIntent=getIntent();
        loggedInUser=(Users)recvIntent.getSerializableExtra("User Object");
        getSupportActionBar().setTitle("Set your Tubelights, "+loggedInUser.name);

        dat= FirebaseDatabase.getInstance();

        tubelightStatus=new ArrayList<>(Arrays.asList(SignedInActivity.tube.getHallTube(),SignedInActivity.tube.getKitchenTube(),SignedInActivity.tube.getBedTube()));
        tubelightNames=new ArrayList<>(Arrays.asList("Hall Tubelight","Kitchen Tubelight","Bedroom Tubelight"));
        tubeSwitchView=findViewById(R.id.tubeLists);
        TubeLightSwitchLayout adapter=new TubeLightSwitchLayout(this,tubelightNames,tubelightStatus);
        tubeSwitchView.setAdapter(adapter);
    }
}

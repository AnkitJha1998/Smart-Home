package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AcActivity extends AppCompatActivity {

    Users loggedInUser;
    AcClass yourAC;
    DatabaseReference ref;
    Switch onOffSwitch;

    public void getACunits()
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                yourAC=dataSnapshot.getValue(AcClass.class);
                if(yourAC.getOnStat()==0)
                    onOffSwitch.setChecked(false);
                else
                    onOffSwitch.setChecked(true);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac);

        loggedInUser=(Users)(getIntent()).getSerializableExtra("User Object");
        yourAC=new AcClass();
        ref= FirebaseDatabase.getInstance().getReference("AC");
        onOffSwitch=findViewById(R.id.onOffSwitch);
        getACunits();

        (getSupportActionBar()).setTitle("AC Control Panel");

        onOffSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch s=(Switch)v;
                if(s.isChecked())
                {
                    yourAC.setOnStat(1);
                    ref.setValue(yourAC);
                }
                else
                {
                    yourAC.setOnStat(0);
                    ref.setValue(yourAC);
                }

            }
        });


    }
}

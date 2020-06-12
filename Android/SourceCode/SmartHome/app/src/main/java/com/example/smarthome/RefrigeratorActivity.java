package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RefrigeratorActivity extends AppCompatActivity {

    RefrigeratorClass myRef;
    DatabaseReference ref;
    Switch onOrOff;
    TextView eggStat,milkStat,vegStat;
    Button rawToggle;
    boolean rawTrue;
    public void toggleValues(View view)
    {
        if(rawTrue==false)
        {
            rawTrue=true;
            rawToggle.setText("Show Normal Values");
            eggStat.setText(String.valueOf(myRef.getEggDistance()));
            milkStat.setText(String.valueOf(myRef.getMilkMeter()));
            vegStat.setText(String.valueOf(myRef.getVegRotMeter()));
        }
        else
        {
            rawTrue=false;
            rawToggle.setText("Raw Values");
            double eggD=myRef.getEggDistance();
            double milkD=myRef.getMilkMeter();
            double vegD=myRef.getVegRotMeter();
            if(eggD<10)
                eggStat.setText("Present");
            else
                eggStat.setText("Empty");
            if(milkD<3)
                milkStat.setText("Empty");
            else if(milkD>=3 && milkD<5)
                milkStat.setText("Less Than Half");
            else
                milkStat.setText("Present");
            if(vegD<250)
                vegStat.setText("Fresh");
            else
                vegStat.setText("Rotten");
        }
    }
    public void getRefrigerator()
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myRef=dataSnapshot.getValue(RefrigeratorClass.class);
                if(myRef.getOnOffSwitch()==1)
                    onOrOff.setChecked(true);
                else
                    onOrOff.setChecked(false);
                double eggD=myRef.getEggDistance();
                double milkD=myRef.getMilkMeter();
                double vegD=myRef.getVegRotMeter();
                if(eggD<10)
                    eggStat.setText("Present");
                else
                    eggStat.setText("Empty");
                if(milkD<3)
                    milkStat.setText("Empty");
                else if(milkD>=3 && milkD<5)
                    milkStat.setText("Less Than Half");
                else
                    milkStat.setText("Present");
                if(vegD<250)
                    vegStat.setText("Fresh");
                else
                    vegStat.setText("Rotten");
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refrigerator);
        (getSupportActionBar()).setTitle("Monitor Your Refrigerator");
        rawTrue=false;
        rawToggle=findViewById(R.id.rawToggle);
        myRef=new RefrigeratorClass();
        onOrOff=findViewById(R.id.onOrOffRefrigerator);
        eggStat=findViewById(R.id.eggRaw);
        milkStat=findViewById(R.id.milkRaw);
        vegStat=findViewById(R.id.vegRaw);
        ref= FirebaseDatabase.getInstance().getReference("Refrigerator");
        getRefrigerator();
        onOrOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Switch temp=(Switch)v;
                if(temp.isChecked())
                    myRef.setOnOffSwitch(1);
                else
                    myRef.setOnOffSwitch(0);
                ref.setValue(myRef);
            }
        });
    }
}

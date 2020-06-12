package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Ref;
import java.util.ArrayList;

public class GroceryActivity extends AppCompatActivity {

    ArrayList<String> items;
    Toolbar tBar;
    DatabaseReference ref;
    RefrigeratorClass fridge;

    void setList()
    {
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                fridge=dataSnapshot.getValue(RefrigeratorClass.class);
                items.clear();
                if(fridge.getEggDistance()>10)
                    items.add("Eggs");
                if(fridge.getMilkMeter()<3)
                    items.add("Milk");
                if(fridge.getVegRotMeter()>250)
                    items.add("Vegetables");
                ArrayAdapter<String> adp=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,items);
                ListView l=findViewById(R.id.itemsView);
                l.setAdapter(adp);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grocery);
        ref= FirebaseDatabase.getInstance().getReference("Refrigerator");
        fridge=new RefrigeratorClass();
        items=new ArrayList<String>();
        tBar=findViewById(R.id.toolbarGrocery);
        setSupportActionBar(tBar);
        setList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.grocery_toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.action_save:
                setList();
                return true;
            default:
                return false;
        }
    }
}

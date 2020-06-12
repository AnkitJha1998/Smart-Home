package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class SignedInActivity extends AppCompatActivity {

    ArrayList<String> devices,desc;
    ListView devicesView;
    toolsLayout layout;
    Users loggedInUser;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    DatabaseReference ref;
    static Tubelights tube;
    ProgressBar pBar;
    NavigationView nView;
    public void getSwitchData()
    {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tube=dataSnapshot.getValue(Tubelights.class);
                pBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signed_in);
        ref = FirebaseDatabase.getInstance().getReference("Tubelights");
        pBar = findViewById(R.id.progressBar);
        getSwitchData();
        Intent inte = getIntent();
        String email = inte.getStringExtra("email");
        for (Users u : MainActivity.userList)
            if (u.getUsername().equals(email))
                loggedInUser = u;
        (getSupportActionBar()).setTitle("Welcome, " + loggedInUser.name);
        drawerLayout = findViewById(R.id.drawerLayout);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        devices = new ArrayList<String>(Arrays.asList("Tubelights", "Refrigerator", "AC"));
        desc = new ArrayList<String>(Arrays.asList("3 Tubelights, one of Hall, one of Kitchen and one of Bedroom", "Fridge Power Control and content checker", "AC of Bedroom"));
        layout = new toolsLayout(this, devices, desc);
        devicesView = findViewById(R.id.toolsToControl);
        devicesView.setAdapter(layout);
        devicesView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent tubelightIntent = new Intent(getApplicationContext(), TubelighActivity.class);
                        tubelightIntent.putExtra("User Object", loggedInUser);
                        startActivity(tubelightIntent);
                        break;
                    case 1:
                        Intent refrigeratorIntent = new Intent(getApplicationContext(), RefrigeratorActivity.class);
                        refrigeratorIntent.putExtra("User Object", loggedInUser);
                        startActivity(refrigeratorIntent);
                        break;
                    case 2:
                        Intent acIntent = new Intent(getApplicationContext(), AcActivity.class);
                        acIntent.putExtra("User Object", loggedInUser);
                        startActivity(acIntent);
                        break;
                }
            }
        });
        nView = findViewById(R.id.navigationDrawer);
        nView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.groceries:
                        Intent grocIntent = new Intent(getApplicationContext(), GroceryActivity.class);
                        startActivity(grocIntent);
                        break;
                    case R.id.about_us:
                        Intent aboutIntent = new Intent(getApplicationContext(), AboutUsActivity.class);
                        startActivity(aboutIntent);
                        break;
                    case R.id.log_out:
                        finish();
                        break;
                }
                return true;
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(toggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onBackPressed() {
    }
}

package com.example.smarthome;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    static public ArrayList<Users> userList;
    DatabaseReference ref;
    EditText userText,passText;

    public void SignUpUser(View view)
    {
        Intent signUpIntent =new Intent(this,SignUpActivity.class);
        startActivity(signUpIntent);
    }
    public void SignInUser(View view)
    {
        String user=userText.getText().toString(),pass=passText.getText().toString();
        if(user.isEmpty() || pass.isEmpty())
            Toast.makeText(getApplicationContext(),"Fields Left empty",Toast.LENGTH_LONG).show();
        else
        {
            boolean found=false;
            for(Users u:userList)
                if(u.username.equals(user) && u.password.equals(pass))
                {
                    found=true;
                    Intent signedIn=new Intent(this,SignedInActivity.class);
                    signedIn.putExtra("email",user);
                    startActivity(signedIn);
                    Toast.makeText(getApplicationContext(),"Signed In Successfully",Toast.LENGTH_SHORT).show();
                    userText.setText("");
                    passText.setText("");
                }
                else if(u.username.equals(user))
                {
                    found=true;
                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();

                }

            if(found==false)
                Toast.makeText(getApplicationContext(),"User not registered",Toast.LENGTH_SHORT).show();
            userText.setText("");
            passText.setText("");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle("Welcome To Smart Home");
        userList=new ArrayList<>();
        ref=FirebaseDatabase.getInstance().getReference("Users");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                userList.clear();
                for(DataSnapshot d:dataSnapshot.getChildren())
                {
                    Users us=d.getValue(Users.class);
                    userList.add(us);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        userText=findViewById(R.id.userText);
        passText=findViewById(R.id.passText);


    }
}

package com.example.smarthome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    DatabaseReference ref;
    EditText nameText,emailText,passText,confPassText;
    TextView stat;
    public void signUp(View view)
    {
        String name,email,pass,confPass;
        name=nameText.getText().toString();
        email=emailText.getText().toString();
        pass=passText.getText().toString();
        confPass=confPassText.getText().toString();
        String id=ref.push().getKey();
        Users temp=new Users();
        temp.id=id;
        temp.name=name;
        temp.username=email;
        temp.password=pass;
        if(name.isEmpty() && email.isEmpty() && pass.isEmpty() && confPass.isEmpty())
            Toast.makeText(getApplicationContext(),"Fields cannot be empty ",Toast.LENGTH_SHORT).show();
        if(!pass.equals(confPass))
            Toast.makeText(getApplicationContext(),"Passwords don't match ",Toast.LENGTH_SHORT).show();
        else
        {
            ref.child(id).setValue(temp);
            Toast.makeText(getApplicationContext(),"Sign Up Successful",Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ref= FirebaseDatabase.getInstance().getReference("Users");
        nameText=findViewById(R.id.nameText);
        emailText=findViewById(R.id.emailText);
        passText=findViewById(R.id.passText);
        confPassText=findViewById(R.id.confPassText);
        stat=findViewById(R.id.passStat);
        confPassText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(confPassText.getText().toString().length()>=passText.getText().toString().length())
                {
                    if ((confPassText.getText().toString()).equals(passText.getText().toString()))
                        stat.setText("Passwords Match");
                    else
                        stat.setText("Passwords Don't Match");
                }
                else
                    stat.setText("");
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}

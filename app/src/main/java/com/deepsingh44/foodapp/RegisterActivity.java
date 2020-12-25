package com.deepsingh44.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepsingh44.foodapp.database.MyDatabase;
import com.deepsingh44.foodapp.model.User;

public class RegisterActivity extends AppCompatActivity {
    private EditText tname, temail, tpass;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        database = new MyDatabase(this);
        tname = findViewById(R.id.regname);
        temail = findViewById(R.id.regemail);
        tpass = findViewById(R.id.regpass);
    }

    public void regCode(View view) {
        if (valid()) {
            //you can do whatever you want
            long t = database.insert(new User(name, email, pass));
            if (t > 0) {
                Toast.makeText(this, "Successfully Register", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Registration Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String name, email, pass;

    private boolean valid() {
        name = tname.getText().toString();
        email = temail.getText().toString();
        pass = tpass.getText().toString();
        if (TextUtils.isEmpty(name)) {
            tname.setError("enter name here");
            tname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(email)) {
            temail.setError("enter email here");
            temail.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(pass)) {
            tpass.setError("enter password here");
            tpass.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    public void goToLogin(View view) {
        Intent in = new Intent(this, LoginActivity.class);
        startActivity(in);
        finish();
    }

}
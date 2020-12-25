package com.deepsingh44.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.deepsingh44.foodapp.database.MyDatabase;
import com.deepsingh44.foodapp.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText temail, tpass;
    private MyDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        database = new MyDatabase(this);
        temail = findViewById(R.id.loginemail);
        tpass = findViewById(R.id.loginpass);
    }

    public void loginCode(View view) {
        if (valid()) {
            User user = database.login(email, pass);
            if (user != null) {
                SingleTask singleTask = (SingleTask) getApplication();
                SharedPreferences.Editor editor=singleTask.getSharedPreferences().edit();
                editor.putString("email",user.getEmail());
                editor.putString("name",user.getName());
                editor.commit();
                Intent in=new Intent(this,HomePage.class);
                startActivity(in);
                finish();
                Toast.makeText(this, "Successfully Login", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String email, pass;

    private boolean valid() {
        email = temail.getText().toString();
        pass = tpass.getText().toString();
        if (TextUtils.isEmpty(email)) {
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

    public void goToRegister(View view) {
        Intent in = new Intent(this, RegisterActivity.class);
        startActivity(in);
        finish();
    }
}
package com.deepsingh44.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        ImageView imageView = findViewById(R.id.mylogo);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_down);

        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                SingleTask singleTask = (SingleTask) getApplication();
                String email = singleTask.getSharedPreferences().getString("email", null);

                if (email != null) {
                    Intent in = new Intent(SplashActivity.this, HomePage.class);
                    startActivity(in);
                    finish();
                } else {
                    Intent in = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(in);
                    finish();
                }


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
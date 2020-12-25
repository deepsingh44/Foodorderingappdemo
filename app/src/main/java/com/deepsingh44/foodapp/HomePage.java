package com.deepsingh44.foodapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deepsingh44.foodapp.model.User;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import ru.nikartm.support.ImageBadgeView;

public class HomePage extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ImageBadgeView mybadge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mybadge = findViewById(R.id.ibv_icon2);


        SingleTask singleTask = (SingleTask) getApplication();

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        final NavigationView navigationView = findViewById(R.id.nav_view);

        View view = navigationView.getHeaderView(0);
        ImageView image = view.findViewById(R.id.profileimage);
        TextView tname = view.findViewById(R.id.profilename);
        TextView temail = view.findViewById(R.id.profileemail);

        tname.setText(singleTask.getSharedPreferences().getString("name", null));
        temail.setText(singleTask.getSharedPreferences().getString("email", null));

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_profile, R.id.nav_add_product, R.id.nav_product_list, R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        mybadge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Navigation.findNavController(navigationView).navigate(R.id.action_nav_home_to_cartFragment);
                navController.navigate(R.id.action_nav_home_to_cartFragment);
            }
        });

    }

    public ImageBadgeView getMybadge() {
        return mybadge;
    }


    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
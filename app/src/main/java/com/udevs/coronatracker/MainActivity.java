package com.udevs.coronatracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    public static SwipeRefreshLayout refreshData;

    public static TextView total_cases,total_deaths,total_recovered;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        implement_UI();
        drawerLayout = findViewById(R.id.drawer);
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigationView);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,toolbar,R.string.drawerOpen,R.string.drawerClose);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.whiteColor));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        fetchData process = new fetchData(MainActivity.this);
        process.execute();
        refresh_data();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch(menuItem.getItemId()){
            case R.id.country:
                Intent country = new Intent(MainActivity.this,all_country.class);
                startActivity(country);
                break;
            case R.id.exit:
                System.exit(1);
                break;
            case R.id.developer:
                Intent developer = new Intent(MainActivity.this,developers.class);
                startActivity(developer);
                break;
        }
        return false;
    }


    public void implement_UI(){
        total_cases = (TextView)findViewById(R.id.cases);
        total_deaths = (TextView)findViewById(R.id.deaths);
        total_recovered = (TextView)findViewById(R.id.recovered);
        refreshData = (SwipeRefreshLayout)findViewById(R.id.refreshData);
    }

    public void refresh_data(){
        refreshData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchData process = new fetchData(MainActivity.this);
                process.execute();
            }
        });
        refreshData.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }
}

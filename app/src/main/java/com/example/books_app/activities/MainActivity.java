package com.example.books_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.books_app.R;
import com.example.books_app.fragments.cart;
import com.example.books_app.fragments.category;
import com.example.books_app.fragments.home;
import com.example.books_app.fragments.profile;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControls();
        addEvents();
    }


    private void addControls() {
        bottomNavigationView=findViewById(R.id.bottom_navigation);
    }

    private void addEvents() {
        bottomNavigationView.setOnItemSelectedListener(navListener);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, new home()).commit();

    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectFragment = null;
            switch (item.getItemId()) {
                case R.id.nav_home:
                    selectFragment = new home();

                    break;
                case R.id.nav_cart:
                    selectFragment = new cart();
                    break;
                case R.id.nav_category:
                    selectFragment = new category();
                    break;
                case R.id.nav_profile:
                    selectFragment = new profile();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.frame_container, selectFragment).commit();
            return true;
        }
    };
}
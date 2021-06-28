package com.example.hanium;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    homeFragment homeFragment = new homeFragment();
    chatFragment chatFragment = new chatFragment();
    alarmFragment alarmFragment = new alarmFragment();
    mypageFragment mypageFragment = new mypageFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                switch(item.getItemId()){
                    case R.id.home:
                        transaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();
                        break;

                    case R.id.chat:
                        transaction.replace(R.id.framelayout, chatFragment).commitAllowingStateLoss();

                        break;

                    case R.id.alarm:
                            transaction.replace(R.id.framelayout, alarmFragment).commitAllowingStateLoss();

                        break;

                    case R.id.mypage:
                        transaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
                        break;
                }

                return true;
            }
        });

    }
    }

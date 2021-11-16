package com.example.hanium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hanium.R;
import com.example.hanium.fragments.chat.chatFragment;
import com.example.hanium.fragments.home.homeFragment;
import com.example.hanium.fragments.log.logFragment;
import com.example.hanium.fragments.mypage.ExchaningPointFragment;
import com.example.hanium.fragments.mypage.ModifyingProfileFragment;
import com.example.hanium.fragments.mypage.MyErrandFragment;
import com.example.hanium.fragments.mypage.mypageFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    FragmentManager fragmentManager = getSupportFragmentManager();
    homeFragment homeFragment = new homeFragment();
    chatFragment chatFragment = new chatFragment();
    logFragment logFragment = new logFragment();
    mypageFragment mypageFragment = new mypageFragment();
    ModifyingProfileFragment modifyingProfileFragment = new ModifyingProfileFragment();
    ExchaningPointFragment exchaningPointFragment = new ExchaningPointFragment();
    MyErrandFragment myErrandFragment = new MyErrandFragment();

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
                switch (item.getItemId()) {
                    case R.id.home:
                        transaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();
                        break;

                    case R.id.chat:
                        transaction.replace(R.id.framelayout, chatFragment).commitAllowingStateLoss();
                        break;
                    case R.id.log:
                        transaction.replace(R.id.framelayout, logFragment).commitAllowingStateLoss();
                        break;
                    case R.id.mypage:
                        transaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
                        break;
                }

                return true;
            }
        });


    }

    public void onClickModifyBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout, modifyingProfileFragment).commitAllowingStateLoss();
    }

    public void onClickBackBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
    }

    public void onClickExchangeBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout, exchaningPointFragment).commitAllowingStateLoss();
    }

    public void onClickErrandBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout, myErrandFragment).commitAllowingStateLoss();
    }
    public void onClickCompleteModifyBtn(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentTransaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            Log.d("test3","test3");
            if(requestCode==0)
                mypageFragment.onActivityResult(requestCode, resultCode, data);
            if(requestCode==1)
                modifyingProfileFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

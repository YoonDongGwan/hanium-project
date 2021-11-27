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
    chatFragment chatFragment;
    logFragment logFragment;
    mypageFragment mypageFragment;
    ModifyingProfileFragment modifyingProfileFragment;
    ExchaningPointFragment exchaningPointFragment;
    MyErrandFragment myErrandFragment;

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
                        if(homeFragment == null){
                            homeFragment = new homeFragment();
                            fragmentManager.beginTransaction().add(R.id.framelayout, homeFragment).commit();
                        }else{
                            fragmentManager.beginTransaction().show(homeFragment).commit();
                        }
                        if(chatFragment != null) fragmentManager.beginTransaction().hide(chatFragment).commit();
                        if(logFragment != null) fragmentManager.beginTransaction().hide(logFragment).commit();
                        if(mypageFragment != null) fragmentManager.beginTransaction().hide(mypageFragment).commit();
                        if(modifyingProfileFragment != null) fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
                        if(exchaningPointFragment != null) fragmentManager.beginTransaction().hide(exchaningPointFragment).commit();
                        if(myErrandFragment != null) fragmentManager.beginTransaction().hide(myErrandFragment).commit();
//                        transaction.replace(R.id.framelayout, homeFragment).commitAllowingStateLoss();
                        break;

                    case R.id.chat:
                        if(chatFragment == null){
                            chatFragment = new chatFragment();
                            fragmentManager.beginTransaction().add(R.id.framelayout, chatFragment).commit();
                        }else{
                            fragmentManager.beginTransaction().show(chatFragment).commit();
                        }
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(logFragment != null) fragmentManager.beginTransaction().hide(logFragment).commit();
                        if(mypageFragment != null) fragmentManager.beginTransaction().hide(mypageFragment).commit();
                        if(modifyingProfileFragment != null) fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
                        if(exchaningPointFragment != null) fragmentManager.beginTransaction().hide(exchaningPointFragment).commit();
                        if(myErrandFragment != null) fragmentManager.beginTransaction().hide(myErrandFragment).commit();
//                        transaction.replace(R.id.framelayout, chatFragment).commitAllowingStateLoss();
                        break;
                    case R.id.log:
                        if(logFragment == null){
                            logFragment = new logFragment();
                            fragmentManager.beginTransaction().add(R.id.framelayout, logFragment).commit();
                        }else{
                            fragmentManager.beginTransaction().show(logFragment).commit();
                        }
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(chatFragment != null) fragmentManager.beginTransaction().hide(chatFragment).commit();
                        if(mypageFragment != null) fragmentManager.beginTransaction().hide(mypageFragment).commit();
                        if(modifyingProfileFragment != null) fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
                        if(exchaningPointFragment != null) fragmentManager.beginTransaction().hide(exchaningPointFragment).commit();
                        if(myErrandFragment != null) fragmentManager.beginTransaction().hide(myErrandFragment).commit();
//                        transaction.replace(R.id.framelayout, logFragment).commitAllowingStateLoss();
                        break;
                    case R.id.mypage:
                        if(mypageFragment == null){
                            mypageFragment = new mypageFragment();
                            fragmentManager.beginTransaction().add(R.id.framelayout, mypageFragment).commit();
                        }else{
                            fragmentManager.beginTransaction().show(mypageFragment).commit();
                        }
                        if(homeFragment != null) fragmentManager.beginTransaction().hide(homeFragment).commit();
                        if(chatFragment != null) fragmentManager.beginTransaction().hide(chatFragment).commit();
                        if(logFragment != null) fragmentManager.beginTransaction().hide(logFragment).commit();
                        if(modifyingProfileFragment != null) fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
                        if(exchaningPointFragment != null) fragmentManager.beginTransaction().hide(exchaningPointFragment).commit();
                        if(myErrandFragment != null) fragmentManager.beginTransaction().hide(myErrandFragment).commit();
//                        transaction.replace(R.id.framelayout, mypageFragment).commitAllowingStateLoss();
                        break;
                }

                return true;
            }
        });


    }

    public void onClickModifyBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        modifyingProfileFragment = new ModifyingProfileFragment();
        fragmentTransaction.add(R.id.framelayout, modifyingProfileFragment).commit();
        fragmentManager.beginTransaction().hide(mypageFragment).commit();
    }

    public void onClickBackBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
        fragmentTransaction.show(mypageFragment).commit();
        if(modifyingProfileFragment != null){
            fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
            modifyingProfileFragment = null;
        }
        if(exchaningPointFragment != null){
            fragmentManager.beginTransaction().hide(exchaningPointFragment).commit();
            exchaningPointFragment = null;
        }
        if(myErrandFragment != null) {
            fragmentManager.beginTransaction().hide(myErrandFragment).commit();
            myErrandFragment = null;
        }
    }

    public void onClickExchangeBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        exchaningPointFragment = new ExchaningPointFragment();
        fragmentTransaction.add(R.id.framelayout, exchaningPointFragment).commit();
        fragmentManager.beginTransaction().hide(mypageFragment).commit();
    }

    public void onClickErrandBtn() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        myErrandFragment = new MyErrandFragment();
        fragmentTransaction.add(R.id.framelayout, myErrandFragment).commit();
        fragmentManager.beginTransaction().hide(mypageFragment).commit();
    }
    public void onClickCompleteModifyBtn(){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
        fragmentManager.beginTransaction().show(mypageFragment).commit();
        fragmentManager.beginTransaction().hide(modifyingProfileFragment).commit();
        modifyingProfileFragment = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode==0)
                mypageFragment.onActivityResult(requestCode, resultCode, data);
            if(requestCode==2)
                Log.d("test5", "test5");
                modifyingProfileFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}

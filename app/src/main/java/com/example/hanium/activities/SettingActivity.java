package com.example.hanium.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SettingActivity extends AppCompatActivity {
    Button setting_back_btn;
    TextView logout,change_password;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    SharedPreferences sharedPreferences;
    String cookie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        logout=findViewById(R.id.logout);
        change_password=findViewById(R.id.change_password);
        setting_back_btn=findViewById(R.id.setting_back_btn);

        logout.setOnClickListener(onClickListener);
        change_password.setOnClickListener(onClickListener);
        setting_back_btn.setOnClickListener(onClickListener);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.change_password:
                    startActivity(new Intent(getApplicationContext(), ChangePWPopup.class));
                    break;
                case R.id.logout:
                    retrofitAPI.logout(cookie).enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            if(response.isSuccessful()) {
                                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                            }else{
                                Log.d("test ",response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                        }
                    });
                    break;
                case R.id.setting_back_btn:
                    finish();
                    break;
            }
        }



    };
}

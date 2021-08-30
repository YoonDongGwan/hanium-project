package com.example.hanium.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class findPopup extends Activity {
    Retrofit retrofit;
    RetrofitAPI retrofitFindAPI;
    Button find_btn;
    EditText find_email, find_phone_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_find);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitFindAPI = retrofit.create(RetrofitAPI.class);

        find_email = findViewById(R.id.find_email);
        find_phone_number = findViewById(R.id.find_phone_number);
        find_btn = findViewById(R.id.find_btn);
        find_btn.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            retrofitFindAPI.find(find_email.getText().toString(),find_phone_number.getText().toString()).enqueue(new Callback<HashMap<String, String>>() {
                @Override
                public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                    if (response.isSuccessful()){
                        Log.d("test","success");

                        finish();
                    }else{

                    }
                }

                @Override
                public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                    Log.d("test","failure"+t.getMessage());
                }
            });
        }
    };
}


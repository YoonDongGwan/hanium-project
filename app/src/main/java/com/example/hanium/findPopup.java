package com.example.hanium;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitFindAPI.find(find_email.getText().toString(),find_phone_number.getText().toString()).enqueue(new Callback<LoginResult>() {
                    @Override
                    public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                        if (response.isSuccessful()){
                            LoginResult result = response.body();
                            Log.d("test","success");

                            finish();
                        }else{
                            Log.d("test",response.errorBody().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResult> call, Throwable t) {
                        Log.d("test","failure"+t.getMessage());
                    }
                });
            }
        });

    }
}


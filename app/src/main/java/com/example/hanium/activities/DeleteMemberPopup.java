package com.example.hanium.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

public class DeleteMemberPopup extends Activity {
    EditText delete_membership_pw;
    Button delete_membership_btn;

    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    String cookie;
    SharedPreferences sharedPreferences;
    MainActivity mainActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_membership);

        delete_membership_pw=findViewById(R.id.delete_membership_pw);
        delete_membership_btn=findViewById(R.id.delete_membership_btn);
        delete_membership_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitAPI.deleteMembership(cookie,delete_membership_pw.getText().toString()).enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if (response.isSuccessful()){
                            Log.d("ss","success");

                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                    }
                });
            }
        });

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);


    }
}



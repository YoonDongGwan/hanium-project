package com.example.hanium.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChangeImageActivity extends Activity {
    TextView find_image, change_basic_image;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    SharedPreferences sharedPreferences;
    String cookie;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_changeimage);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);


        find_image=findViewById(R.id.find_image);
        change_basic_image=findViewById(R.id.change_basic_image);
        find_image.setOnClickListener(onClickListener);
        change_basic_image.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.find_image:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    startActivityForResult(intent, 1);
                    setResult(2,intent);
                case R.id.change_basic_image:
                    retrofitAPI.setBasicImage(cookie).enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            finish();
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                        }
                    });
            }
        }
    };
}

package com.example.hanium.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviewPopup extends Activity {
    ImageButton add_btn,sub_btn;
    int count=1;
    TextView review_point;
    Button confirmBtn;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    String cookie, id;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review_popup);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);

        Intent intent = getIntent();
        id = intent.getStringExtra("id");

        review_point = findViewById(R.id.review_point);
        add_btn = findViewById(R.id.review_add_btn);
        sub_btn = findViewById(R.id.review_sub_btn);
        confirmBtn = findViewById(R.id.review_point_confirmBtn);

        add_btn.setOnClickListener(onClickListener);
        sub_btn.setOnClickListener(onClickListener);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.review_add_btn:
                    if(count<5){
                        count++;
                        review_point.setText(count+"");
                    }
                    break;
                case R.id.review_sub_btn:
                    if(count>1) {
                        count--;
                        review_point.setText(count + "");
                    }
                    break;
                case R.id.review_point_confirmBtn:
                    retrofitAPI.setReviewPoint(cookie,id,count);
                    break;
            }
        }
    };
}

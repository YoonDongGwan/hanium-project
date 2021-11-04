package com.example.hanium.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;

import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;

import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.adapters.TextRecyclerAdapter;
import com.example.hanium.classes.SearchLocation;
import com.example.hanium.classes.locationinfo;
import com.example.hanium.classes.posts;
import com.example.hanium.server.HomePostsResult;
import com.example.hanium.server.LocationinfoResult;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerResult;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ChangeLocationActivity extends AppCompatActivity {
    EditText search;
    Button back;
    String key,cookie;
    RecyclerView recyclerView;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    ArrayList<SearchLocation> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changelocation);
        search = findViewById(R.id.changelocation_activity_edittext);
        recyclerView = findViewById(R.id.changelocation_activity_recyclerview);
        back = findViewById(R.id.changelocation_activity_back);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        search.setText(key);


        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getAddrSearchResult(key,cookie).enqueue(new Callback<LocationinfoResult>() {
            @Override
            public void onResponse(Call<LocationinfoResult> call, Response<LocationinfoResult> response) {
                if(response.isSuccessful()){
                    list.clear();
                    for(int i=0;i<response.body().getData().getResult().size();i++)
                        list.add(response.body().getData().getResult().get(i));
                     Log.d("te","성공");
                     recyclerView.setLayoutManager(new LinearLayoutManager(ChangeLocationActivity.this));
                     TextRecyclerAdapter adapter = new TextRecyclerAdapter(list);
                     recyclerView.setAdapter(adapter);
                }else{

                }

            }

            @Override
            public void onFailure(Call<LocationinfoResult> call, Throwable t) {
                Log.d("tes",t.getMessage().toString());
                Log.d("test","통신실패");
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SetLocationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() != KeyEvent.ACTION_UP) && keyCode == KeyEvent.KEYCODE_ENTER){
                    Intent intent = new Intent(ChangeLocationActivity.this, ChangeLocationActivity.class);
                    intent.putExtra("key",search.getText().toString());
                    startActivity(intent);
                    search.clearFocus();
                    search.setText(null);
                    finish();
                    return true;

                }
                return false;
            }
        });


    }





}

package com.example.hanium.activities;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerScope;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SetLocationActivity extends AppCompatActivity {
    ImageButton add_btn,sub_btn;
    TextView distance_count,baselocation;
    Button back_btn,changelocation_btn;
    String location;
    int count=1;
    Intent intent;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    SharedPreferences sharedPreferences;
    String cookie;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlocation);
        back_btn=findViewById(R.id.set_location_back);
        distance_count=findViewById(R.id.distance_count);
        baselocation=findViewById(R.id.baseloaction);
        changelocation_btn=findViewById(R.id.changlocation_btn);
        distance_count.setText(count+"");
        add_btn=findViewById(R.id.add_btn);
        sub_btn=findViewById(R.id.sub_btn);

        intent = getIntent();
        location = intent.getStringExtra("location");

        baselocation.setText(location);
        baselocation.setTextSize(15);

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);



        changelocation_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(getApplicationContext(),ChangeLocationActivity.class);
                intent.putExtra("key",baselocation.getText().toString());
                startActivity(intent);
                finish();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                distance_count.setText(count+"");
            }
        });

        sub_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count>1) {
                    count--;
                    distance_count.setText(count + "");
                }
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitAPI.setScope(cookie,Integer.parseInt(distance_count.getText().toString())).enqueue(new Callback<ServerScope>() {
                    @Override
                    public void onResponse(Call<ServerScope> call, Response<ServerScope> response) {
                        if(response.isSuccessful()) {
                            Log.d("test ", "success");
                        }else{
                            Log.d("test ",response.toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerScope> call, Throwable t) {
                        Log.d("test ","failure"+t.getMessage());
                    }
                });
                Intent intent2 = new Intent();
                intent2.putExtra("location",location);
                setResult(0,intent2);
                finish();
            }
        });
    }

}

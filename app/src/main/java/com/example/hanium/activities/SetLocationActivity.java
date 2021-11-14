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
import com.example.hanium.server.ServerResult;
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
    Button back_btn,changelocation_btn, confirmBtn;
    String ADMNM, districtId = null;
    int count=1;
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
        confirmBtn = findViewById(R.id.setLocationConfirmBtn);

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

        getAddress();

        confirmBtn.setOnClickListener(onClickListener);
        changelocation_btn.setOnClickListener(onClickListener);
        add_btn.setOnClickListener(onClickListener);
        sub_btn.setOnClickListener(onClickListener);
        back_btn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch(view.getId()){
                case R.id.changlocation_btn:
                    Intent intent1 = new Intent(getApplicationContext(),ChangeLocationActivity.class);
                    intent1.putExtra("key",baselocation.getText().toString());
                    startActivityForResult(intent1,0);
                    break;
                case R.id.add_btn:
                    count++;
                    distance_count.setText(count+"");
                    break;
                case R.id.sub_btn:
                    if(count>1) {
                        count--;
                        distance_count.setText(count + "");
                    }
                    break;
                case R.id.set_location_back:
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
                    intent2.putExtra("location", ADMNM);
                    setResult(RESULT_OK,intent2);
                    finish();
                    break;
                case R.id.setLocationConfirmBtn:
                    retrofitAPI.setAddressScope(cookie, count).enqueue(new Callback<ServerResult>() {
                        @Override
                        public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                            if (response.isSuccessful()){
                                Log.d("success message",response.body().getMessage());
                            }
                        }
                        @Override
                        public void onFailure(Call<ServerResult> call, Throwable t) {
                        }
                    });
                    setResult(RESULT_OK);
                    finish();
                    break;
            }
        }
    };
    private void getAddress(){
        retrofitAPI.getAddress(cookie).enqueue(new Callback<ServerResult>() {
            @Override
            public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                if (response.isSuccessful()) {
                    ServerResult result = response.body();
                    baselocation.setText(result.getData().get("simpleAddress"));
                    distance_count.setText(result.getData().get("addressScope"));
                }
            }
            @Override
            public void onFailure(Call<ServerResult> call, Throwable t) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            String districtId = data.getStringExtra("districtId");
            retrofitAPI.putAddress(cookie, districtId).enqueue(new Callback<ServerResult>() {
                @Override
                public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                    if (response.isSuccessful()){
                        getAddress();
                    }
                }
                @Override
                public void onFailure(Call<ServerResult> call, Throwable t) {
                }
            });
        }
    }
}

package com.example.hanium.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.util.HashMap;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    EditText signup_email, signup_authenticationnumber, signup_nickname, signup_name, signup_password, signup_confirm_pwd, signup_phonenumber;
    Button signup_btn, signup_emailsend_btn, signup_authenticationconfirm_btn;
    String cookie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button back = findViewById(R.id.signup_back);
        back.setOnClickListener(onClickListener);

        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);

        signup_email = findViewById(R.id.signup_email);
        signup_nickname = findViewById(R.id.signup_nickname);
        signup_name = findViewById(R.id.signup_name);
        signup_password = findViewById(R.id.signup_password);
        signup_confirm_pwd = findViewById(R.id.signup_confirm_pwd);
        signup_phonenumber = findViewById(R.id.signup_phonenumber);
        signup_authenticationnumber = findViewById(R.id.signup_authenticationnumber);
        signup_emailsend_btn = findViewById(R.id.signup_emailsend_btn);
        signup_authenticationconfirm_btn = findViewById(R.id.signup_authenticationconfirm_btn);

        signup_emailsend_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitAPI.send(signup_email.getText().toString()).enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", "success");
                        } else {

                            Log.d("test1", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        Log.d("test", "failure" + t.getMessage());
                    }
                });
            }
        });
        signup_btn = findViewById(R.id.signup_btn);
        signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrofitAPI.signup(signup_email.getText().toString(), signup_nickname.getText().toString(),
                        signup_name.getText().toString(), signup_password.getText().toString(),
                        signup_confirm_pwd.getText().toString(), signup_phonenumber.getText().toString()).enqueue(new Callback<HashMap<String, String>>() {
                    @Override
                    public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                        if (response.isSuccessful()) {
                            Log.d("test", "success");
                        } else {

                            Log.d("test1", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                        Log.d("test", "failure" + t.getMessage());
                    }
                });
            }
        });
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
}

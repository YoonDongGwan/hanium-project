package com.example.hanium.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    Retrofit retrofit;
    RetrofitAPI retrofitLoginAPI;
    EditText login_email, login_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login_btn = findViewById(R.id.login_btn);
        TextView sign_up = findViewById(R.id.sign_up);
        TextView find = findViewById(R.id.find);
        login_btn.setOnClickListener(onClickListener);
        sign_up.setOnClickListener(onClickListener);
        find.setOnClickListener(onClickListener);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitLoginAPI = retrofit.create(RetrofitAPI.class);

    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.login_btn:
                    retrofitLoginAPI.login(/*login_email.getText().toString(),login_password.getText().toString()*/"cjfwnd123zz@gmail.com","1234asdf@").enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            if (response.isSuccessful()){
                                Log.d("test","success");
                                Log.d("test",response.headers().get("Set-Cookie"));
                                SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("Cookie",response.headers().get("Set-Cookie"));
                                editor.commit();
                                intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                            Log.d("test",response.toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                            Log.d("test","failure");
                        }
                    });
                    break;
                case R.id.sign_up:
                    intent = new Intent(getApplicationContext(),SignUpActivity.class);
                    startActivity(intent);
                    break;
                case R.id.find:
                    intent = new Intent(getApplicationContext(), findPopup.class);
                    startActivity(intent);
                    break;
            }
        }
    };
}
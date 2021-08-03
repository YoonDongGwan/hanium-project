package com.example.hanium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
                    retrofitLoginAPI.login(login_email.getText().toString(),login_password.getText().toString()).enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                            if (response.isSuccessful()){
                                LoginResult result = response.body();
                                Log.d("test","success");

                                intent = new Intent(getApplicationContext(),MainActivity.class);
                                startActivity(intent);
                            }else{
                            Log.d("test",response.errorBody().toString());
                            }
                        }

                        @Override
                        public void onFailure(Call<LoginResult> call, Throwable t) {
                            Log.d("test","failure"+t.getMessage());
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
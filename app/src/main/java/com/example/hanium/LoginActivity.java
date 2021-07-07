package com.example.hanium;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button login_btn = findViewById(R.id.login_btn);
        TextView signup_btn = findViewById(R.id.signup_btn);
        login_btn.setOnClickListener(onClickListener);
        signup_btn.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.login_btn:
                    intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.signup_btn:
                    intent = new Intent(getApplicationContext(),SignUpActivity.class);
                    startActivity(intent);
                    break;

            }
        }
    };
}
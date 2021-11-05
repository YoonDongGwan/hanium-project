package com.example.hanium.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.hanium.R;
import com.example.hanium.fragments.mypage.mypageFragment;

public class SetLocationActivity extends AppCompatActivity {
    ImageButton add_btn,sub_btn;
    TextView distance_count,baselocation;
    Button back_btn,changelocation_btn;
    String location;
    int count=1;
    Intent intent;


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
                finish();
            }
        });
    }

}

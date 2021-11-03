package com.example.hanium.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.hanium.R;

public class SetLocationActivity extends AppCompatActivity {
    ImageButton add_btn,sub_btn;
    TextView distance_count;
    Button back_btn;
    MainActivity mainActivity;
    int count=1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setlocation);
        back_btn=findViewById(R.id.set_location_back);
        distance_count=findViewById(R.id.distance_count);
        distance_count.setText(count+"");
        add_btn=findViewById(R.id.add_btn);
        sub_btn=findViewById(R.id.sub_btn);

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
    }

}

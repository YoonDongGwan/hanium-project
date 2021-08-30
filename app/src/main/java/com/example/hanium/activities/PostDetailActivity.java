package com.example.hanium.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hanium.R;
import com.example.hanium.adapters.ImageViewPagerAdapter;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {
    ArrayList<Integer> images = new ArrayList<>();
    ViewPager viewPager;
    Button clent_info_btn, back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        viewPager = findViewById(R.id.imageviewpager);
        clent_info_btn = findViewById(R.id.client_info_btn);
        back = findViewById(R.id.detail_back);
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        ImageViewPagerAdapter viewPagerAdapter = new ImageViewPagerAdapter(getApplicationContext(),images);
        viewPager.setAdapter(viewPagerAdapter);
        clent_info_btn.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
            case R.id.client_info_btn:
                Intent intent = new Intent(getApplicationContext(),ClientInfoPopUp.class);
                startActivity(intent);
                break;
            case R.id.detail_back:
                finish();
                break;
            }
        }
    };
}

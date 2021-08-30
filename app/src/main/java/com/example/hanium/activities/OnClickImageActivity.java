package com.example.hanium.activities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hanium.R;
import com.example.hanium.adapters.ClickImageViewPagerAdpater;

import java.util.ArrayList;

public class OnClickImageActivity extends AppCompatActivity {
    ArrayList<Integer> images = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickimage);
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        ViewPager viewPager = findViewById(R.id.imagebook_viewpager);
        ClickImageViewPagerAdpater adpater = new ClickImageViewPagerAdpater(getApplicationContext(),images);
        viewPager.setAdapter(adpater);
    }
}

package com.example.hanium;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class PostDetailActivity extends AppCompatActivity {
    ArrayList<Integer> images = new ArrayList<>();
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        images.add(R.drawable.image1);
        images.add(R.drawable.image2);
        images.add(R.drawable.image3);
        viewPager = findViewById(R.id.imageviewpager);
        ImageViewPagerAdapter viewPagerAdapter = new ImageViewPagerAdapter(getApplicationContext(),images);
        viewPager.setAdapter(viewPagerAdapter);
    }
}

package com.example.hanium.activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hanium.R;
import com.example.hanium.adapters.ClickImageViewPagerAdpater;

import java.util.ArrayList;

public class OnClickImageActivity extends AppCompatActivity {
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onclickimage);
        Intent intent = getIntent();
        int size = intent.getIntExtra("size",0);
        for (int i = 0; i<size; i++){
            byte[] byteArray = getIntent().getByteArrayExtra("bitmap"+i);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
            bitmaps.add(bitmap);
        }
        ViewPager viewPager = findViewById(R.id.imagebook_viewpager);
        ClickImageViewPagerAdpater adpater = new ClickImageViewPagerAdpater(getApplicationContext(),bitmaps);
        viewPager.setAdapter(adpater);
    }
}

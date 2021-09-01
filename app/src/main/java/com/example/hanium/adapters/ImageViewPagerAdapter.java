package com.example.hanium.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.PagerAdapter;

import com.example.hanium.activities.OnClickImageActivity;
import com.example.hanium.R;

import java.util.ArrayList;

public class ImageViewPagerAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Bitmap> bitmaps;

    public ImageViewPagerAdapter(Context context, ArrayList<Bitmap> bitmaps){
        this.context = context;
        this.bitmaps = bitmaps;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view == (View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.viewpager_item,container,false);
        ImageView imageView = view.findViewById(R.id.postdetail_imageview);
        imageView.setImageBitmap(bitmaps.get(position));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(container.getContext(), OnClickImageActivity.class);
                ContextCompat.startActivity(container.getContext(),intent,null);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}

package com.example.hanium.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hanium.R;
import com.example.hanium.adapters.ImageViewPagerAdapter;
import com.example.hanium.classes.postInfo;
import com.example.hanium.classes.sellerInfo;
import com.example.hanium.server.PostDetailResult;
import com.example.hanium.server.RetrofitAPI;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PostDetailActivity extends AppCompatActivity {
    ArrayList<String> images = new ArrayList<>();
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    ViewPager viewPager;
    Button clent_info_btn, back;
    TextView title, description, price, price2, requiredTime, deadline, createAt, sellingDistrict, nickname;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        viewPager = findViewById(R.id.imageviewpager);
        clent_info_btn = findViewById(R.id.client_info_btn);
        back = findViewById(R.id.detail_back);
        title = findViewById(R.id.detail_title);
        description = findViewById(R.id.detail_description);
        price = findViewById(R.id.detail_price);
        price2 = findViewById(R.id.detail_price2);
        requiredTime = findViewById(R.id.detail_requiredTime);
        deadline = findViewById(R.id.detail_deadline);
        createAt = findViewById(R.id.detail_createAt);
        sellingDistrict = findViewById(R.id.detail_sellingDistrict);
        nickname = findViewById(R.id.detail_nickname);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getDetail(id,cookie).enqueue(callback);

        clent_info_btn.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);


    }
    Callback<PostDetailResult> callback = new Callback<PostDetailResult>() {
        @Override
        public void onResponse(Call<PostDetailResult> call, Response<PostDetailResult> response) {
            if (response.isSuccessful()){
                postInfo postinfo = response.body().getData().getPostInfo();
                sellerInfo sellerinfo = response.body().getData().getSellerInfo();
                nickname.setText(sellerinfo.getNickname());
                title.setText(postinfo.getTitle());
                Log.d("test",postinfo.getCreatedAt().toString());
                createAt.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(postinfo.getCreatedAt()));
                price.setText(postinfo.getPrice()+"P");
                price2.setText(postinfo.getPrice()+"P");
                sellingDistrict.setText(postinfo.getSellingDistrict());
                deadline.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(postinfo.getDeadline()));
                requiredTime.setText(postinfo.getRequiredTime());
                description.setText(postinfo.getDescription());
                images = postinfo.getPostImages();
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(images.get(0));
                            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                            connection.setDoInput(true);
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            bitmaps.add(BitmapFactory.decodeStream(inputStream));
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                try {
                    thread.join();
                    ImageViewPagerAdapter viewPagerAdapter = new ImageViewPagerAdapter(getApplicationContext(),bitmaps);
                    viewPager.setAdapter(viewPagerAdapter);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                Log.d("test",response.toString());
            }
        }

        @Override
        public void onFailure(Call<PostDetailResult> call, Throwable t) {
            Log.d("test",call.toString());
            Log.d("test",t.getMessage().toString());
        }
    };
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

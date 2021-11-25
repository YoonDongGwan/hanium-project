package com.example.hanium.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.hanium.R;
import com.example.hanium.adapters.ImageViewPagerAdapter;
import com.example.hanium.classes.postInfo;
import com.example.hanium.classes.sellerInfo;
import com.example.hanium.server.PostDetailResult;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

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
    Button client_info_btn, back, delete_btn, edit_btn;
    TextView title, description, price, price2, requiredTime, deadline, createAt, sellingDistrict, nickname;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    String cookie, id, myNickname = "0", sellerNickname = "1", mannerPoint, profileImages, simpleAddress, sellCount, buyCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        viewPager = findViewById(R.id.imageviewpager);
        client_info_btn = findViewById(R.id.client_info_btn);
        delete_btn = findViewById(R.id.detail_delete_btn);
        edit_btn = findViewById(R.id.detail_edit_btn);
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
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getUser(cookie).enqueue(new Callback<ServerResult>() {
            @Override
            public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                if(response.isSuccessful()){
                    myNickname = response.body().getData().get("nickname");
                }
            }
            @Override
            public void onFailure(Call<ServerResult> call, Throwable t) {
            }
        });
        retrofitAPI.getDetail(id,cookie).enqueue(callback);

        client_info_btn.setOnClickListener(onClickListener);
        back.setOnClickListener(onClickListener);
    }
    Callback<PostDetailResult> callback = new Callback<PostDetailResult>() {
        @Override
        public void onResponse(Call<PostDetailResult> call, Response<PostDetailResult> response) {
            if (response.isSuccessful()){
                postInfo postinfo = response.body().getData().getPostInfo();
                sellerInfo sellerinfo = response.body().getData().getSellerInfo();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                dateFormat.setTimeZone(TimeZone.getTimeZone("Etc/GMT"));
                sellerNickname = sellerinfo.getNickname();
                if(myNickname.equals(sellerNickname)) {
                    edit_btn.setVisibility(View.VISIBLE);
                    delete_btn.setVisibility(View.VISIBLE);
                    edit_btn.setOnClickListener(onClickListener);
                    delete_btn.setOnClickListener(onClickListener);
                }
                nickname.setText(sellerNickname);
                title.setText(postinfo.getTitle());
                createAt.setText(dateFormat.format(postinfo.getCreatedAt()));
                price.setText(postinfo.getPrice()+"P");
                price2.setText(postinfo.getPrice()+"P");
                sellingDistrict.setText(postinfo.getSellingDistrict());
                deadline.setText(dateFormat.format(postinfo.getDeadline()));
                requiredTime.setText(postinfo.getRequiredTime());
                description.setText(postinfo.getDescription());
                images = postinfo.getPostImages();
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            for(int i = 0; i < images.size(); i++){
                                URL url = new URL(images.get(i));
                                HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                                connection.setDoInput(true);
                                connection.connect();
                                InputStream inputStream = connection.getInputStream();
                                bitmaps.add(BitmapFactory.decodeStream(inputStream));
                            }
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
                mannerPoint = sellerinfo.getMannerPoint();
                profileImages = sellerinfo.getProfileImages();
                simpleAddress = sellerinfo.getSimpleAdrress();
                sellCount = String.valueOf(sellerinfo.getSellCount());
                buyCount = String.valueOf(sellerinfo.getBuyCount());
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(PostDetailActivity.this);
                builder.setMessage("이미 삭제된 게시물입니다.").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        alertDialog.getButton(DialogInterface.BUTTON_POSITIVE).setTextColor(Color.rgb(255,65,65));
                    }
                });
                alertDialog.show();
            }
        }

        @Override
        public void onFailure(Call<PostDetailResult> call, Throwable t) {

        }
    };
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
            case R.id.client_info_btn:
                Intent intent = new Intent(getApplicationContext(),ClientInfoPopUp.class);
                intent.putExtra("nickname", sellerNickname);
                intent.putExtra("mannerPoint", mannerPoint);
                intent.putExtra("profileImages", profileImages);
                intent.putExtra("simpleAddress", simpleAddress);
                intent.putExtra("sellCount", sellCount);
                intent.putExtra("buyCount", buyCount);
                startActivity(intent);
                break;
            case R.id.detail_back:
                finish();
                PostDetailActivity.this.overridePendingTransition(R.anim.enter_from_left, R.anim.exit_to_right);
                break;
            case R.id.detail_edit_btn:
                Intent intent1 = new Intent(getApplicationContext(), EditPostActivity.class);
                intent1.putExtra("id", id);
                intent1.putExtra("title", title.getText().toString());
                intent1.putExtra("deadline", deadline.getText().toString());
                intent1.putExtra("requiredTime", requiredTime.getText().toString());
                intent1.putExtra("price", price.getText().toString());
                intent1.putExtra("description", description.getText().toString());
                intent1.putStringArrayListExtra("images", images);
                startActivity(intent1);
                overridePendingTransition(R.anim.enter_from_right, R.anim.exit_to_left);
                finish();
                break;
            case R.id.detail_delete_btn:
                retrofitAPI.deletePost(cookie, id).enqueue(new Callback<ServerResult>() {
                    @Override
                    public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
                        if (response.isSuccessful()){
                            Log.d("test","삭제 성공");
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Call<ServerResult> call, Throwable t) {
                        Log.d("test",t.getMessage().toString());
                    }
                });
                break;
            }
        }
    };
}

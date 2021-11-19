package com.example.hanium.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.hanium.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class ClientInfoPopUp extends Activity {
    String nickname, mannerPoint, profileImages, simpleAddress, sellCount, buyCount;
    ImageView profile;
    TextView tvNickname, tvMannerPoint, tvSimpleAddress, tvSellCount, tvBuyCount;
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clientinfo);
        tvNickname = findViewById(R.id.clientInfo_nickname);
        tvMannerPoint = findViewById(R.id.clientInfo_mannerPoint);
        tvSimpleAddress = findViewById(R.id.clientInfo_simpleAddress);
        tvSellCount = findViewById(R.id.clientInfo_sellCount);
        tvBuyCount = findViewById(R.id.clientInfo_buyCount);
        profile = findViewById(R.id.clientInfo_profile);

        Intent intent = getIntent();
        nickname = intent.getStringExtra("nickname");
        mannerPoint = intent.getStringExtra("mannerPoint");
        profileImages = intent.getStringExtra("profileImages");
        simpleAddress = intent.getStringExtra("simpleAddress");
        sellCount = intent.getStringExtra("sellCount");
        buyCount = intent.getStringExtra("buyCount");

        tvNickname.setText(nickname);
        tvMannerPoint.setText(mannerPoint);
        tvSimpleAddress.setText(simpleAddress);
        tvSellCount.setText(sellCount);
        tvBuyCount.setText(buyCount);

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    URL url = new URL(profileImages);
                    HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream inputStream = connection.getInputStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
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
            profile.setImageBitmap(bitmap);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

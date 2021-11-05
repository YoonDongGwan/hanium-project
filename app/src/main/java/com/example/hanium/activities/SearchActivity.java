package com.example.hanium.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.server.HomePostsResult;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.classes.posts;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {
    EditText search;
    TextView latest_order, price_order, closing_order;
    Button back, cancel, filter;
    RecyclerView recyclerView;
    String key, cookie;
    String order_on = "updatedAt";
    String filter_on = "0";
    CardView order;
    BottomSheetDialog dialog;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    ArrayList<posts> post_list = new ArrayList<>();
    ArrayList<String> url_list = new ArrayList<>();
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_search);
        search = findViewById(R.id.search_activity_edittext);
        recyclerView = findViewById(R.id.search_activity_recyclerview);
        back = findViewById(R.id.search_activity_back);
        order = findViewById(R.id.search_order);
        latest_order = findViewById(R.id.latest_order);
        price_order = findViewById(R.id.price_order);
        closing_order = findViewById(R.id.closing_order);
        cancel = findViewById(R.id.dialog_cancel);
        filter = findViewById(R.id.search_filter);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        search.setText(key);

        back.setOnClickListener(onClickListener);
        order.setOnClickListener(onClickListener);
        filter.setOnClickListener(onClickListener);
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() != KeyEvent.ACTION_UP) && keyCode == KeyEvent.KEYCODE_ENTER){
                    Intent intent = new Intent(SearchActivity.this, SearchActivity.class);
                    intent.putExtra("key",search.getText().toString());
                    startActivity(intent);
                    search.clearFocus();
                    search.setText(null);
                    finish();
                    return true;
                }
                return false;
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        GetSearchList(order_on,filter_on);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.search_activity_back:
                    finish();
                    break;
                case R.id.search_order:
                    dialog = new BottomSheetDialog(SearchActivity.this);
                    dialog.setContentView(R.layout.bottomsheetdialog);
                    dialog.setCanceledOnTouchOutside(true);
                    TextView latest_order = dialog.findViewById(R.id.latest_order);
                    TextView price_order = dialog.findViewById(R.id.price_order);
                    TextView closing_order = dialog.findViewById(R.id.closing_order);
                    Button dialog_cancel = dialog.findViewById(R.id.dialog_cancel);
                    latest_order.setOnClickListener(onClickListenerInDialog);
                    price_order.setOnClickListener(onClickListenerInDialog);
                    closing_order.setOnClickListener(onClickListenerInDialog);
                    dialog_cancel.setOnClickListener(onClickListenerInDialog);
                    dialog.show();
                    break;
                case R.id.search_filter:
                    if(filter_on.equals("0")){
                        filter_on = "1";
                        filter.setText("모든 심부름 보기");
                        filter.setBackground(ContextCompat.getDrawable(SearchActivity.this, R.drawable.login_btn_bg));
                        filter.setTextColor(Color.rgb(255,255,255));
                        GetSearchList(order_on, filter_on);
                    }else{
                        filter_on = "0";
                        filter.setText("심부름 불가능 안보기");
                        filter.setBackground(ContextCompat.getDrawable(SearchActivity.this, R.drawable.login_btn_bg_grey));
                        filter.setTextColor(Color.rgb(0,0,0));
                        GetSearchList(order_on, filter_on);
                    }
                    break;
            }
        }
    };
    View.OnClickListener onClickListenerInDialog = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.latest_order:
                    order_on = "updatedAt";
                    GetSearchList(order_on, filter_on);
                    dialog.dismiss();
                    break;
                case R.id.price_order:
                    order_on = "price";
                    GetSearchList(order_on, filter_on);
                    dialog.dismiss();
                    break;
                case R.id.closing_order:
                    order_on = "deadline";
                    GetSearchList(order_on, filter_on);
                    dialog.dismiss();
                    break;
                case R.id.dialog_cancel:
                    dialog.dismiss();
                    break;
            }
        }
    };
    private void GetSearchList(String order, String filter){
        retrofitAPI.getSearchResult(key,order,filter,"1",cookie).enqueue(new Callback<HomePostsResult>() {
            @Override
            public void onResponse(Call<HomePostsResult> call, Response<HomePostsResult> response) {
                if (response.isSuccessful()){
                    post_list.clear();
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        post_list.add(response.body().getData().get(i));
                        url_list.add(response.body().getData().get(i).getThumbnail());
                    }
                    Thread thread = new Thread(){
                        @Override
                        public void run() {
                            try {
                                for(int i = 0; i < url_list.size(); i++){
                                    URL url = new URL(url_list.get(i));
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
                        recyclerView.setLayoutManager(new LinearLayoutManager(SearchActivity.this));
                        RecyclerAdapter adapter = new RecyclerAdapter(post_list, bitmaps);
                        recyclerView.setAdapter(adapter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<HomePostsResult> call, Throwable t) {

            }
        });
    }
    }


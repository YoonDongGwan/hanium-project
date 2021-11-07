package com.example.hanium.fragments.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.classes.posts;
import com.example.hanium.classes.sellerInfo;
import com.example.hanium.server.AllHistoryResponse;
import com.example.hanium.server.RetrofitAPI;

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

public class LogThirdFragment extends Fragment {
    ArrayList<posts> sellerEnd = new ArrayList<>();
    ArrayList<String> url_list = new ArrayList<>();
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_third, container, false);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie","");

        recyclerView = v.findViewById(R.id.logthirdRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getAllHistory(cookie).enqueue(new Callback<AllHistoryResponse>() {
            @Override
            public void onResponse(Call<AllHistoryResponse> call, Response<AllHistoryResponse> response) {
                if (response.isSuccessful()){
                    sellerEnd = response.body().getData().getSellerEnd();
                    for (int i = 0; i < sellerEnd.size(); i++){
                        url_list.add(sellerEnd.get(i).getThumbnail());
                    }Thread thread = new Thread(){
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
                        adapter = new RecyclerAdapter(sellerEnd, bitmaps);
                        recyclerView.setAdapter(adapter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<AllHistoryResponse> call, Throwable t) {

            }
        });

        return v;
    }
}

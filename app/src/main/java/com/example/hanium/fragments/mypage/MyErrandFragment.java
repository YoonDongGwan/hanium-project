package com.example.hanium.fragments.mypage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hanium.adapters.ErrandAdapter;
import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.classes.ErrandPost;
import com.example.hanium.activities.MainActivity;
import com.example.hanium.R;
import com.example.hanium.classes.posts;
import com.example.hanium.server.HomePostsResult;
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

public class MyErrandFragment extends Fragment {
    Button back_btn;
    ArrayList<posts> post_list = new ArrayList<>();
    ArrayList<String> url_list = new ArrayList<>();
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    MainActivity mainActivity;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_my_errand, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.errandRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie","");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getMyPosts(cookie).enqueue(new Callback<HomePostsResult>() {
            @Override
            public void onResponse(Call<HomePostsResult> call, Response<HomePostsResult> response) {
                if (response.isSuccessful()){
                    for (int i=0; i<response.body().getData().size(); i++) {
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
                        RecyclerAdapter adapter = new RecyclerAdapter(post_list, bitmaps);
                        recyclerView.setAdapter(adapter);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    Log.d("test",response.toString());
                }
            }

            @Override
            public void onFailure(Call<HomePostsResult> call, Throwable t) {

            }
        });

        back_btn = v.findViewById(R.id.my_errand_back);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onClickBackBtn();
            }
        });
        return v;
    }
}
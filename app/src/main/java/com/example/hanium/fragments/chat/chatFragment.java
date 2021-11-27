package com.example.hanium.fragments.chat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.adapters.ChatRecyclerAdapter;
import com.example.hanium.classes.ChatRoomInformation;
import com.example.hanium.server.ChatRoomsResponse;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerResult;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class chatFragment extends Fragment {
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.chatrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie", "");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getChatRooms(cookie).enqueue(new Callback<ChatRoomsResponse>() {
            @Override
            public void onResponse(Call<ChatRoomsResponse> call, Response<ChatRoomsResponse> response) {
                if(response.isSuccessful()){
                    if(response.body().getData() != null){
                        ArrayList<ChatRoomInformation> rooms = response.body().getData();
                        ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(rooms);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ChatRoomsResponse> call, Throwable t) {

            }
        });
        return v;
    }

}

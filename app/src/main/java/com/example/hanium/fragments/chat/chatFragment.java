package com.example.hanium.fragments.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.adapters.ChatRecyclerAdapter;
import com.example.hanium.classes.chatinf;

import java.util.ArrayList;
import java.util.List;

public class chatFragment extends Fragment {
    List<com.example.hanium.classes.chatinf> chatinf = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        chatinf.add(new chatinf("너굴맨","강아지 산책시켜주세요"));
        chatinf.add(new chatinf("너굴맨","강아지 산책시켜주세요"));
        chatinf.add(new chatinf("너굴맨","강아지 산책시켜주세요"));
        RecyclerView recyclerView = v.findViewById(R.id.chatrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        ChatRecyclerAdapter adapter = new ChatRecyclerAdapter(chatinf);
        recyclerView.setAdapter(adapter);
        return v;
    }

}

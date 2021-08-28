package com.example.hanium;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class LogFirstFragment extends Fragment {
    ArrayList<post> post_list = new ArrayList<post>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_first, container, false);
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
//        RecyclerView recyclerView = v.findViewById(R.id.logfirstRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecyclerAdapter adapter = new RecyclerAdapter(post_list);
//        recyclerView.setAdapter(adapter);
        return v;
    }
}

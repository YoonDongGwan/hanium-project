package com.example.hanium.fragments.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hanium.R;
import com.example.hanium.classes.post;

import java.util.ArrayList;

public class LogSecondFragment extends Fragment {
    ArrayList<post> post_list = new ArrayList<post>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_second, container, false);
//        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
//        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
//        RecyclerView recyclerView = v.findViewById(R.id.logsecondRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecyclerAdapter adapter = new RecyclerAdapter(post_list);
//        recyclerView.setAdapter(adapter);
        return v;
    }
}

package com.example.hanium.fragments.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.hanium.R;
import com.example.hanium.classes.sellerInfo;

import java.util.ArrayList;

public class LogThirdFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_third, container, false);
//        RecyclerView recyclerView = v.findViewById(R.id.logthirdRecyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        RecyclerAdapter adapter = new RecyclerAdapter(post_list);
//        recyclerView.setAdapter(adapter);
        return v;
    }
}

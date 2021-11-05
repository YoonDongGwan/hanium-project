package com.example.hanium.fragments.mypage;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hanium.adapters.ErrandAdapter;
import com.example.hanium.classes.ErrandPost;
import com.example.hanium.activities.MainActivity;
import com.example.hanium.R;

import java.util.ArrayList;

public class MyErrandFragment extends Fragment {
    Button back_btn;
    ArrayList<ErrandPost> post_list = new ArrayList<ErrandPost>();

    MainActivity mainActivity;

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
        ErrandAdapter adapter = new ErrandAdapter(post_list);
        recyclerView.setAdapter(adapter);
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
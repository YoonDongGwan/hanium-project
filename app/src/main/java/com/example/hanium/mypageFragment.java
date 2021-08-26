package com.example.hanium;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class mypageFragment extends Fragment {
    Button modify_profile_btn;
    Button exchange_point_btn;
    Button my_errand_btn;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mypage, container, false);
        modify_profile_btn = v.findViewById(R.id.modify_profile_btn);
        exchange_point_btn = v.findViewById(R.id.exchange_point_btn);
        my_errand_btn=v.findViewById(R.id.my_errand_btn);

        modify_profile_btn.setOnClickListener(onClickListener);
        exchange_point_btn.setOnClickListener(onClickListener);
        my_errand_btn.setOnClickListener(onClickListener);
        return v;
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.modify_profile_btn:
                    mainActivity.onClickModifyBtn();
                    break;
                case R.id.exchange_point_btn:
                    mainActivity.onClickExchangeBtn();
                    break;
                case R.id.my_errand_btn:
                    mainActivity.onClickErrandBtn();
                    break;
            }
        }
    };
}

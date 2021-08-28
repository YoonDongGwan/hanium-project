package com.example.hanium;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ModifyingProfileFragment extends Fragment {
    Button back_btn;
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
        View v = inflater.inflate(R.layout.fragment_modifying_profile, container, false);
        back_btn = v.findViewById(R.id.modify_profile_back);
        back_btn.setOnClickListener(onClickListener);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getModify("connect.sid=s%3AWV-zCXyDyB40vvfJGxmrftsNl5XmD02u.qXFMQhowZI86TgrtpLnLAi3fnzgh0GvrYc%2FtKi%2BXDKI").enqueue(callback);
        return v;
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mainActivity.onClickBackBtn();
        }
    };
    Callback<MypageUserResult> callback = new Callback<MypageUserResult>() {
        @Override
        public void onResponse(Call<MypageUserResult> call, Response<MypageUserResult> response) {
            if (response.isSuccessful()){
                Log.d("test2",response.body().getSuccess());
            }else{
                Log.d("test2",response.toString());
            }
        }

        @Override
        public void onFailure(Call<MypageUserResult> call, Throwable t) {

        }
    };
}

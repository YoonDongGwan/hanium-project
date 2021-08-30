package com.example.hanium.fragments.mypage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hanium.activities.MainActivity;
import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchaningPointFragment extends Fragment {
    Button back_btn;
    TextView currentCash;
    MainActivity mainActivity;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity=(MainActivity)getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity=null;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_exchanging_point,container,false);
        back_btn=v.findViewById(R.id.exchanging_point_back);
        currentCash = v.findViewById(R.id.currentCash);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie","");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getPoint(cookie).enqueue(callback);

        back_btn.setOnClickListener(onClickListener);
        return v;
    }
    Callback<HashMap<String, String>> callback = new Callback<HashMap<String, String>>() {
        @Override
        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
            if (response.isSuccessful()){
                currentCash.setText(response.body().get("data"));
            }else{
            }
        }

        @Override
        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

        }
    };
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mainActivity.onClickBackBtn();
        }
    };
}

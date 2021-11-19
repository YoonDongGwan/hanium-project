package com.example.hanium.fragments.mypage;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hanium.R;
import com.example.hanium.activities.MainActivity;
import com.example.hanium.activities.SetLocationActivity;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerResult;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class mypageFragment extends Fragment {
    Button modify_profile_btn, exchange_point_btn, my_errand_btn;
    ImageButton location_change_btn;
    TextView nickname, simpleAddress, mannerPoint, cash;
    ImageView profile;
    MainActivity mainActivity;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    Bitmap bitmap;
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
        my_errand_btn = v.findViewById(R.id.my_errand_btn);
        location_change_btn=v.findViewById(R.id.location_change_btn);
        profile = v.findViewById(R.id.mypage_profile);
        nickname = v.findViewById(R.id.nickname);
        simpleAddress = v.findViewById(R.id.simpleAddress);
        mannerPoint = v.findViewById(R.id.mannerPoint);
        cash = v.findViewById(R.id.cash);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getUser(cookie).enqueue(callback);
        modify_profile_btn.setOnClickListener(onClickListener);
        exchange_point_btn.setOnClickListener(onClickListener);
        my_errand_btn.setOnClickListener(onClickListener);
        location_change_btn.setOnClickListener(onClickListener);

        return v;
    }
    Callback<ServerResult> callback = new Callback<ServerResult>() {
        @Override
        public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
            if(response.isSuccessful()){
                Thread thread = new Thread(){
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(response.body().getData().get("profileImageURI"));
                            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                            connection.setDoInput(true);
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
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
                    profile.setImageBitmap(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nickname.setText(response.body().getData().get("nickname"));
                simpleAddress.setText(response.body().getData().get("simpleAddress"));
                mannerPoint.setText(response.body().getData().get("mannerPoint"));
                cash.setText(response.body().getData().get("cash"));
            }else{
                Log.d("test",response.toString());
            }
        }

        @Override
        public void onFailure(Call<ServerResult> call, Throwable t) {
            Log.d("test","통신 실패");
        }
    };

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
                case R.id.location_change_btn:
                    getActivity().startActivityForResult(new Intent(getContext(), SetLocationActivity.class),0);
                    break;
            }
        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
        Log.d("test4","test4");
    }
}

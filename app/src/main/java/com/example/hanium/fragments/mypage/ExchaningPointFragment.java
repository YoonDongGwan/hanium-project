package com.example.hanium.fragments.mypage;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.hanium.activities.MainActivity;
import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;

import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ExchaningPointFragment extends Fragment {
    Button back_btn,charge_btn,refund_btn;
    TextView currentCash;
    EditText charge_bank,charge_account,charge_amount,refund_bank,refund_account,refund_amount;
    MainActivity mainActivity;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    SharedPreferences sharedPreferences;
    String cookie;

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
        charge_btn=v.findViewById(R.id.charge_btn);
        refund_btn=v.findViewById(R.id.refund_btn);
        charge_bank=v.findViewById(R.id.charge_bank);
        charge_amount=v.findViewById(R.id.charge_amount);
        charge_account=v.findViewById(R.id.charge_account);
        refund_bank=v.findViewById(R.id.refund_bank);
        refund_account=v.findViewById(R.id.refund_account);
        refund_amount=v.findViewById(R.id.refund_amount);
        currentCash = v.findViewById(R.id.currentCash);


        sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);



        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();

        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getPoint(cookie).enqueue(callback);

        back_btn.setOnClickListener(onClickListener);
        charge_btn.setOnClickListener(onClickListener);
        refund_btn.setOnClickListener(onClickListener);
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
            switch (v.getId()) {
                case R.id.exchanging_point_back:
                    mainActivity.onClickBackBtn();
                    break;
                case R.id.charge_btn:
                    retrofitAPI.charge(cookie,charge_amount.getText().toString(),charge_bank.getText().toString(),charge_account.getText().toString()).enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            if (response.isSuccessful()) {
                                Log.d("test", "success");
                            }else{
                                Log.d("test",response.message());
                            }
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {
                                Log.d("test",t.getMessage().toString());
                        }
                    });

            }

        }
    };
}

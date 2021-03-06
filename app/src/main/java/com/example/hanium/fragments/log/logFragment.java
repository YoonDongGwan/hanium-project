package com.example.hanium.fragments.log;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hanium.R;
import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.adapters.ViewPagerAdapter;
import com.example.hanium.classes.posts;
import com.example.hanium.server.AllHistoryResponse;
import com.example.hanium.server.HomePostsResult;
import com.example.hanium.server.RetrofitAPI;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class logFragment extends Fragment {
    LogFirstFragment buyerUsageFragment = new LogFirstFragment();
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_log, container, false);

        ViewPager2 viewPager = v.findViewById(R.id.viewpager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(),getLifecycle());

        adapter.addFragment(new LogFirstFragment());
        adapter.addFragment(new LogSecondFragment());
        adapter.addFragment(new LogThirdFragment());
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = v.findViewById(R.id.tablayout);
        String[] tabLabel = {"????????????","?????????","????????????"};
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                viewPager.setSaveEnabled(false);
                tab.setText(tabLabel[position]);
            }
        }).attach();

        return v;
    }
}

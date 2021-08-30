package com.example.hanium.fragments.log;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.hanium.R;
import com.example.hanium.adapters.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class logFragment extends Fragment {
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
        String[] tabLabel = {"부름내역","부림중","부림완료"};
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

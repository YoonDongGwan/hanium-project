package com.example.hanium.fragments.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.activities.AddPostActivity;
import com.example.hanium.R;
import com.example.hanium.adapters.RecyclerAdapter;
import com.example.hanium.server.HomePostsResult;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.activities.SearchActivity;
import com.example.hanium.classes.posts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeFragment extends Fragment {
    ArrayList<posts> post_list = new ArrayList<posts>();
    Button add_btn;
    EditText search;
    RecyclerView recyclerView;
    RecyclerAdapter adapter;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getPosts("connect.sid=s%3AWV-zCXyDyB40vvfJGxmrftsNl5XmD02u.qXFMQhowZI86TgrtpLnLAi3fnzgh0GvrYc%2FtKi%2BXDKI").enqueue(new Callback<HomePostsResult>() {
            @Override
            public void onResponse(Call<HomePostsResult> call, Response<HomePostsResult> response) {
                if (response.isSuccessful()){
                    for (int i=0; i<response.body().getData().size(); i++) {
                        post_list.add(response.body().getData().get(i));
                    }
                    recyclerView = v.findViewById(R.id.homeRecyclerView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapter = new RecyclerAdapter(post_list);
                    recyclerView.setAdapter(adapter);
                }else{
                    Log.d("test",response.toString());
                }
            }

            @Override
            public void onFailure(Call<HomePostsResult> call, Throwable t) {
                    Log.d("test","실패");
            }
        });

//        recyclerView.addOnScrollListener(onScrollListener);

        add_btn = v.findViewById(R.id.home_add_post_btn);
        search = v.findViewById(R.id.home_search_edittext);
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AddPostActivity.class);
                startActivity(intent);
            }
        });
        search.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((event.getAction() != KeyEvent.ACTION_UP) && keyCode == KeyEvent.KEYCODE_ENTER){
                    Intent intent = new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("key",search.getText().toString());
                    startActivity(intent);
                    search.clearFocus();
                    search.setText(null);
                    return true;
                }
                return false;
            }
        });
        return v;
    }
//    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
//        @Override
//        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//            super.onScrolled(recyclerView, dx, dy);
//            LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
//            int totalItemCount = layoutManager.getItemCount();
//            int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
//            if(lastVisible>=totalItemCount-1){
//                post_list.add(new post("test1","test","test","test"));
//                post_list.add(new post("test2","test","test","test"));
//                post_list.add(new post("test3","test","test","test"));
//                adapter.notifyItemRangeInserted(lastVisible+1,3);
//            }
//        }
//    };
}

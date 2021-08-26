package com.example.hanium;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class homeFragment extends Fragment {
    ArrayList<post> post_list = new ArrayList<post>();
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
        post_list.add(new post("집에 바퀴벌레좀 잡아주세요ㅠㅠ","위치 - 시흥 은행","마감시간 - 21.07.12 13:00","예상소요시간 - 10"));
        post_list.add(new post("맛집 줄서주세요!!","위치 - 시흥 신천동","마감시간 - 21.07.21 18:00","예상소요시간 - 30"));
        post_list.add(new post("해열제 진통제 사다주실분","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));
        post_list.add(new post("강아지 산책해주세요","위치 - 부천 상동","마감시간 - 21.07.07 20:00","예상소요시간 - 1시간"));

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getposts("connect.sid=s%3A7Jxy9YGA9TFHS4SCLp_Cc8JewFc0yEwY.KTj%2FzucOBzr5kxT7D4l4zLPi8i6Y8yDO2eOhfYgvXWY").enqueue(new Callback<HomePostsResult>() {
            @Override
            public void onResponse(Call<HomePostsResult> call, Response<HomePostsResult> response) {
                if (response.isSuccessful()){
                    Log.d("test1",response.body().getSuccess());
                    Log.d("test1",response.body().getMessage());
                    Log.d("test1",response.body().getPosts().get(0).getTitle());
                }else{
                    Log.d("test",response.toString());
                }
            }

            @Override
            public void onFailure(Call<HomePostsResult> call, Throwable t) {
                    Log.d("test","실패");
            }
        });

        recyclerView = v.findViewById(R.id.homeRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new RecyclerAdapter(post_list);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnScrollListener(onScrollListener);

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
    RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            LinearLayoutManager layoutManager = LinearLayoutManager.class.cast(recyclerView.getLayoutManager());
            int totalItemCount = layoutManager.getItemCount();
            int lastVisible = layoutManager.findLastCompletelyVisibleItemPosition();
            if(lastVisible>=totalItemCount-1){
                post_list.add(new post("test1","test","test","test"));
                post_list.add(new post("test2","test","test","test"));
                post_list.add(new post("test3","test","test","test"));
                adapter.notifyItemRangeInserted(lastVisible+1,3);
            }
        }
    };
}

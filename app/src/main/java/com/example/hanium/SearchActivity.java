package com.example.hanium;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    EditText search;
    Button back;
    RecyclerView recyclerView;
    ArrayList<post> list = new ArrayList<>();
    String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_search);
        search = findViewById(R.id.search_activity_edittext);
        recyclerView = findViewById(R.id.search_activity_recyclerview);
        back = findViewById(R.id.search_activity_back);
        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        search.setText(key);
        search.setOnKeyListener(onKeyListener);
        back.setOnClickListener(onClickListener);

        list.add(new post("강아지 산책","destination","deadline","time"));
        list.add(new post("쓰레기 분리수기","destination","deadline","time"));
        list.add(new post("쓰레기 산책","destination","deadline","time"));

        SearchOnList(list,key);

    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };
    View.OnKeyListener onKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((event.getAction() != KeyEvent.ACTION_UP) && keyCode == KeyEvent.KEYCODE_ENTER){
                search.clearFocus();
                EditText search_edittext = findViewById(v.getId());
                String key = search_edittext.getText().toString();
                SearchOnList(list,key);
                return true;
            }
            return false;
        }
    };
    private void SearchOnList(ArrayList<post> list, String key){
        ArrayList<post> result_list = new ArrayList<>();
        if(key!=null){
            for(int i=0; i< list.size(); i++){
                String title = list.get(i).getTitle();
                if(title.contains(key)){
                    result_list.add(list.get(i));
                }
            }
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        RecyclerAdapter adapter = new RecyclerAdapter(result_list);
        recyclerView.setAdapter(adapter);
    }
}

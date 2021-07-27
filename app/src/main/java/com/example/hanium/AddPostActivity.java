package com.example.hanium;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AddPostActivity extends AppCompatActivity {
    ImageButton add_image;
    Button back;
    RecyclerView recyclerView;
    AddImageRecyclerAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        add_image = findViewById(R.id.add_image);
        back = findViewById(R.id.post_back);
        back.setOnClickListener(onClickListener);
        add_image.setOnClickListener(onClickListener);
        recyclerView = findViewById(R.id.addpost_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_image:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(intent,1);
                    break;
                case R.id.post_back:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                ArrayList<Uri> list = new ArrayList<>();
                list.add(null);
                ClipData clipData = data.getClipData();
                if (clipData != null){
                for (int i=0; i<clipData.getItemCount(); i++){
                    list.add(clipData.getItemAt(i).getUri());
                    }
                }
                else{
                    list.add(data.getData());
                }
                adapter = new AddImageRecyclerAdapter(list);
                recyclerView.setAdapter(adapter);
            }
        }
    }
}

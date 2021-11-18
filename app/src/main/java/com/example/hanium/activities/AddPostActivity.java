package com.example.hanium.activities;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hanium.R;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.adapters.AddImageRecyclerAdapter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class AddPostActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    ImageButton add_image;
    Button back,post_btn;
    RecyclerView recyclerView;
    AddImageRecyclerAdapter adapter;
    EditText title,description,price,deadline_YYYY,deadline_MM,deadline_DD,deadline_HH,
            deadline_mm,requiredTime_HH,requiredTime_MM;
    String requiredTime,deadline;
    File file;
    InputStream inputStream = null;
    Bitmap bitmap;
    ByteArrayOutputStream byteArrayOutputStream;
    SharedPreferences sharedPreferences;
    String cookie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addpost);
        add_image = findViewById(R.id.add_image);
        back = findViewById(R.id.post_back);

        recyclerView = findViewById(R.id.addpost_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
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
        title = findViewById(R.id.errand_title);
        description=findViewById(R.id.errand_description);
        price = findViewById(R.id.errand_price);
        deadline_YYYY = findViewById(R.id.deadline_YYYY);
        deadline_MM = findViewById(R.id.deadline_MM);
        deadline_DD = findViewById(R.id.deadline_DD);
        deadline_HH = findViewById(R.id.deadline_HH);
        deadline_mm = findViewById(R.id.deadline_mm);
        deadline = deadline_YYYY.getText().toString() + "-" + deadline_MM.getText().toString() + "-"+deadline_DD.getText().toString()
                + " " + deadline_HH.getText().toString() + ":" + deadline_mm.getText().toString() + ":00";
        requiredTime_HH = findViewById(R.id.requiredTime_HH);
        requiredTime_MM = findViewById(R.id.requiredTime_MM);
        requiredTime = requiredTime_HH.getText().toString() + "시간" + requiredTime_MM.getText().toString() + "분";

        post_btn=findViewById(R.id.post_btn);
        back.setOnClickListener(onClickListener);
        add_image.setOnClickListener(onClickListener);
        post_btn.setOnClickListener(onClickListener);

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
                case R.id.post_btn:

//                    RequestBody bodyTitle = RequestBody.create(title.getText().toString(), MediaType.parse("text/plain"));
//                    RequestBody bodyDescription = RequestBody.create(description.getText().toString(), MediaType.parse("text/plain"));
//                    RequestBody bodyPrice = RequestBody.create(price.getText().toString(), MediaType.parse("text/plain"));
                    RequestBody bodyRequiredTime = RequestBody.create(requiredTime, MediaType.parse("text/plain"));
                    RequestBody bodyDeadline = RequestBody.create(deadline, MediaType.parse("text/plain"));
//                    retrofitAPI.addPost(cookie, bodyTitle, bodyDescription, bodyPrice, bodyDeadline, bodyRequiredTime,);
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
                        file = new File(clipData.getItemAt(i).getUri().getPath());
                        inputStream = null;
                        try {
                            inputStream = getApplicationContext().getContentResolver().openInputStream(clipData.getItemAt(i).getUri());
                        }catch(IOException e) {
                            e.printStackTrace();
                        }
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
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

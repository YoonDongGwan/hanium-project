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
import com.example.hanium.adapters.AddImageRecyclerAdapter;
import com.example.hanium.adapters.ImageViewPagerAdapter;
import com.example.hanium.server.RetrofitAPI;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditPostActivity extends AppCompatActivity {
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    ImageButton add_image;
    Button back, edit_btn;
    RecyclerView recyclerView;
    AddImageRecyclerAdapter adapter;
    EditText title,description,price,deadline_YYYY,deadline_MM,deadline_DD,deadline_HH,
            deadline_mm,requiredTime_HH,requiredTime_MM;
    String requiredTime, deadline;
    File file;
    Bitmap bitmap;
    ByteArrayOutputStream byteArrayOutputStream;
    SharedPreferences sharedPreferences;
    String cookie, id;
    ArrayList<MultipartBody.Part> images = new ArrayList<>();
    ArrayList<String> urlList = null;
    ArrayList<Bitmap> bitmaps = new ArrayList<>();
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpost);
        add_image = findViewById(R.id.editPost_addImage);
        back = findViewById(R.id.editPost_back);
        title = findViewById(R.id.editPost_errand_title);
        description=findViewById(R.id.editPost_errand_description);
        price = findViewById(R.id.editPost_errand_price);
        deadline_YYYY = findViewById(R.id.editPost_deadline_YYYY);
        deadline_MM = findViewById(R.id.editPost_deadline_MM);
        deadline_DD = findViewById(R.id.editPost_deadline_DD);
        deadline_HH = findViewById(R.id.editPost_deadline_HH);
        deadline_mm = findViewById(R.id.editPost_deadline_mm);

        requiredTime_HH = findViewById(R.id.editPost_requiredTime_HH);
        requiredTime_MM = findViewById(R.id.editPost_requiredTime_MM);

        edit_btn =findViewById(R.id.editPost_btn);
        recyclerView = findViewById(R.id.editPost_recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this,5));

        intent = getIntent();
        id = intent.getStringExtra("id");
        deadline = intent.getStringExtra("deadline");
        requiredTime = intent.getStringExtra("requiredTime").replace("시간","").replace("분","");
        String[] requiredTimeArray = requiredTime.split(" ");

        urlList = intent.getStringArrayListExtra("images");
        bitmaps.add(null);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    for(int i = 0; i < urlList.size(); i++){
                        URL url = new URL(urlList.get(i));
                        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
                        connection.setDoInput(true);
                        connection.connect();
                        InputStream inputStream = connection.getInputStream();
                        bitmap = BitmapFactory.decodeStream(inputStream);
                        bitmaps.add(bitmap);
                        byteArrayOutputStream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray());
                        MultipartBody.Part image = MultipartBody.Part.createFormData("images", "image",requestBody);
                        images.add(image);
                    }
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
            adapter = new AddImageRecyclerAdapter(null, bitmaps);
            recyclerView.setAdapter(adapter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        title.setText(intent.getStringExtra("title"));
        description.setText(intent.getStringExtra("description"));
        price.setText(intent.getStringExtra("price").replace("P",""));

        deadline_YYYY.setText(deadline.substring(0, 4));
        deadline_MM.setText(deadline.substring(5, 7));
        deadline_DD.setText(deadline.substring(8, 10));
        deadline_HH.setText(deadline.substring(11, 13));
        deadline_mm.setText(deadline.substring(14, 16));

        requiredTime_HH.setText(requiredTimeArray[0]);
        requiredTime_MM.setText(requiredTimeArray[1]);

        sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie","");

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        back.setOnClickListener(onClickListener);
        add_image.setOnClickListener(onClickListener);
        edit_btn.setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.editPost_addImage:
                    Intent intent = new Intent();
                    intent.setType("image/*");
                    intent.setAction(Intent.ACTION_GET_CONTENT);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(intent,1);
                    break;
                case R.id.editPost_back:
                    Intent intent1 = new Intent(EditPostActivity.this, PostDetailActivity.class);
                    intent1.putExtra("id", id);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.editPost_btn:
                    deadline = deadline_YYYY.getText().toString() + "-" + deadline_MM.getText().toString() + "-"+deadline_DD.getText().toString()
                            + " " + deadline_HH.getText().toString() + ":" + deadline_mm.getText().toString() + ":00";
                    requiredTime = requiredTime_HH.getText().toString() + "시간 " + requiredTime_MM.getText().toString() + "분";

                    RequestBody bodyTitle = RequestBody.create(MediaType.parse("text/plain"), title.getText().toString());
                    RequestBody bodyDescription = RequestBody.create(MediaType.parse("text/plain"), description.getText().toString());
                    RequestBody bodyPrice = RequestBody.create(MediaType.parse("text/plain"), price.getText().toString());
                    RequestBody bodyRequiredTime = RequestBody.create(MediaType.parse("text/plain"), requiredTime);
                    RequestBody bodyDeadline = RequestBody.create(MediaType.parse("text/plain"), deadline);

                    retrofitAPI.editPost(cookie, id, bodyTitle, bodyDescription, bodyPrice, bodyDeadline, bodyRequiredTime, images).enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            if(response.isSuccessful()) {
                                Intent intent1 = new Intent(EditPostActivity.this, PostDetailActivity.class);
                                intent1.putExtra("id", id);
                                startActivity(intent1);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                        }
                    });

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
                images.clear();
                if (clipData != null){
                    for (int i=0; i<clipData.getItemCount(); i++){
                        Uri uri = clipData.getItemAt(i).getUri();
                        list.add(uri);
                        addImagesForMultipart(uri);
                    }
                }
                else{
                    Log.d("test","2");
                    Uri uri = data.getData();
                    list.add(uri);
                    addImagesForMultipart(uri);
                }
                adapter = new AddImageRecyclerAdapter(list, null);
                recyclerView.setAdapter(adapter);
            }
        }
    }
    private void addImagesForMultipart(Uri uri){
        file = new File(uri.getPath());
        InputStream inputStream = null;
        try {
            inputStream = getApplicationContext().getContentResolver().openInputStream(uri);
        }catch(IOException e) {
            e.printStackTrace();
        }
        bitmap = BitmapFactory.decodeStream(inputStream);
        byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray());
        MultipartBody.Part image = MultipartBody.Part.createFormData("images", file.getName() ,requestBody);
        images.add(image);
    }

}

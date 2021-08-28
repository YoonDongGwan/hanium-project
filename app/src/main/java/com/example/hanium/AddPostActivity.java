package com.example.hanium;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    HashMap<String, RequestBody> map;
    Retrofit retrofit;
    RetrofitAPI retrofitFindAPI;
    ImageButton add_image;
    Button back,post_btn;
    RecyclerView recyclerView;
    AddImageRecyclerAdapter adapter;
    EditText title,description,price,deadline_YYYY,deadline_MM,deadline_DD,deadline_HH,
            deadline_mm,requiredTime_HH,requiredTime_MM;
    String requiredTime,deadline;

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


        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        clientBuilder.addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build();
        retrofitFindAPI = retrofit.create(RetrofitAPI.class);
        title=findViewById(R.id.errand_title);
        description=findViewById(R.id.errand_description);
        price=findViewById(R.id.errand_price);
        deadline_YYYY=findViewById(R.id.deadline_YYYY);
        deadline_MM=findViewById(R.id.deadline_MM);
        deadline_DD=findViewById(R.id.deadline_DD);
        deadline_HH=findViewById(R.id.deadline_HH);
        deadline_mm=findViewById(R.id.deadline_mm);
        deadline=deadline_YYYY.getText().toString()+"-"+deadline_MM.getText().toString()+"-"+deadline_DD.getText().toString()
                +"-"+deadline_HH.getText().toString()+":"+deadline_mm.getText().toString();
        requiredTime_HH=findViewById(R.id.requiredTime_HH);
        requiredTime_MM=findViewById(R.id.requiredTime_MM);
        requiredTime=requiredTime_HH.getText().toString()+"-"+requiredTime_MM.getText().toString();

        map=new HashMap<>();
        RequestBody body_title = RequestBody.create(MediaType.parse("text/plain"),title.getText().toString());
        RequestBody body_description = RequestBody.create(MediaType.parse("text/plain"),description.getText().toString());
        RequestBody body_price = RequestBody.create(MediaType.parse("text/plain"),price.getText().toString());
        RequestBody body_deadline = RequestBody.create(MediaType.parse("text/plain"), deadline);
        RequestBody body_requiredTime = RequestBody.create(MediaType.parse("text/plain"),requiredTime);

        map.put("title", body_title);
        map.put("description", body_description);
        map.put("price", body_price);
        map.put("deadline", body_deadline);
        map.put("requiredTime", body_requiredTime);
        
//        File file = new File(filepath);
//        InputStream inputStream = null;
//        try {
//            inputStream = getContext().getContentResolver().openInputStream(photoUri);
//        }catch(IOException e) {
//            e.printStackTrace();
//        }
//        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
//        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray());
//        MultipartBody.Part uploadFile = MultipartBody.Part.createFormData("postImg", file.getName() ,requestBody);
//
        post_btn=findViewById(R.id.post_btn);
//        post_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                retrofitFindAPI.post("connect.sid=s%3Afp_-yzxmNdx9ZpFQqwr-7nPNPv-7zINy.7HNCmO4w9SAoCI142L%2FT3Rt3qDT9lQD9%2FyVltsFxQlg",title.getText().toString(),description.getText().toString(),
//                        Integer.parseInt(price.getText().toString()),deadline,requiredTime).enqueue(new Callback<ConfirmResult>() {
//                    @Override
//                    public void onResponse(Call<ConfirmResult> call, Response<ConfirmResult> response) {
//                        if (response.isSuccessful()){
//                            ConfirmResult result = response.body();
//                            Log.d("test","success");
//
//                        }else{
//
//                            Log.d("test1",response.message());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ConfirmResult> call, Throwable t) {
//                        Log.d("test","failure"+t.getMessage());
//                    }
//                });
//            }
//        });
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

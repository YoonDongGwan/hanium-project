package com.example.hanium.fragments.mypage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.hanium.R;
import com.example.hanium.activities.MainActivity;
import com.example.hanium.server.RetrofitAPI;
import com.example.hanium.server.ServerResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
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

public class ModifyingProfileFragment extends Fragment {
    HashMap<String, RequestBody> map;
    Button back_btn;
    MainActivity mainActivity;
    ImageView modify_profile_image;
    Button modify_image_btn, modify_profile_complete_btn;
    EditText modify_profile_name, modify_profile_nickname, modify_profile_phonenum, modify_profile_password;
    Bitmap bitmap;
    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    String cookie;
    MultipartBody.Part uploadFile;
    ByteArrayOutputStream byteArrayOutputStream;
    File file;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modifying_profile, container, false);
        back_btn = v.findViewById(R.id.modify_profile_back);
        back_btn.setOnClickListener(onClickListener);
        modify_image_btn = v.findViewById(R.id.modify_image_btn);
        modify_image_btn.setOnClickListener(onClickListener);
        modify_profile_complete_btn = v.findViewById(R.id.modify_profile_complete_btn);
        modify_profile_complete_btn.setOnClickListener(onClickListener);
        modify_profile_image = v.findViewById(R.id.modify_profile_image);
        modify_profile_name = v.findViewById(R.id.modify_profile_name);
        modify_profile_nickname = v.findViewById(R.id.modify_profile_nickname);
        modify_profile_phonenum = v.findViewById(R.id.modify_profile_phonenum);
        modify_profile_password = v.findViewById(R.id.modify_profile_password);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        cookie = sharedPreferences.getString("Cookie", "");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.145.19:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);
        retrofitAPI.getModify(cookie).enqueue(callback);

        map = new HashMap<>();

        return v;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.modify_profile_back:
                    mainActivity.onClickBackBtn();
                    break;
                case R.id.modify_image_btn:
                    final CharSequence[] oItems = {"사진 찾기", "기본 이미지 변경"};

                    AlertDialog.Builder oDialog = new AlertDialog.Builder(getContext(),
                            android.R.style.Theme_DeviceDefault_Light_Dialog_Alert);

                    oDialog.setTitle("사진 변경")
                            .setItems(oItems, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (which == 0) {
                                        Intent intent = new Intent();
                                        intent.setType("image/*");
                                        intent.setAction(Intent.ACTION_GET_CONTENT);
                                        startActivityForResult(intent, 2);
                                    } else {
                                        retrofitAPI.setBasicImage(cookie).enqueue(new Callback<HashMap<String, String>>() {
                                            @Override
                                            public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {

                                            }

                                            @Override
                                            public void onFailure(Call<HashMap<String, String>> call, Throwable t) {

                                            }
                                        });
                                    }
                                }
                            }).setCancelable(false)
                            .show();
                    break;

                case R.id.modify_profile_complete_btn:
                    RequestBody body_nickname = RequestBody.create(MediaType.parse("text/plain"), modify_profile_nickname.getText().toString());
                    RequestBody body_name = RequestBody.create(MediaType.parse("text/plain"), modify_profile_name.getText().toString());
                    RequestBody body_phoneNum = RequestBody.create(MediaType.parse("text/plain"), modify_profile_phonenum.getText().toString());
                    RequestBody body_password = RequestBody.create(MediaType.parse("text/plain"), modify_profile_password.getText().toString());
                    map.put("nickname", body_nickname);
                    map.put("name", body_name);
                    map.put("phoneNumber", body_phoneNum);
                    map.put("password", body_password);
                    retrofitAPI.modify(cookie, uploadFile, map).enqueue(new Callback<HashMap<String, String>>() {
                        @Override
                        public void onResponse(Call<HashMap<String, String>> call, Response<HashMap<String, String>> response) {
                            if (response.isSuccessful()) {
                                mainActivity.onClickCompleteModifyBtn();
                                modify_profile_password.setText("");
                            } else {

                                Log.d("test1", response.message());
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
    Callback<ServerResult> callback = new Callback<ServerResult>() {
        @Override
        public void onResponse(Call<ServerResult> call, Response<ServerResult> response) {
            if (response.isSuccessful()) {
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        try {
                            URL url = new URL(response.body().getData().get("profileImageURI"));
                            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                            connection.setDoInput(true);
                            connection.connect();
                            InputStream inputStream = connection.getInputStream();
                            bitmap = BitmapFactory.decodeStream(inputStream);
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
                    modify_profile_image.setImageBitmap(bitmap);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                modify_profile_name.setText(response.body().getData().get("name"));
                modify_profile_nickname.setText(response.body().getData().get("nickname"));
                modify_profile_phonenum.setText(response.body().getData().get("phoneNumber"));
            } else {
                Log.d("test", response.toString());
            }
        }

        @Override
        public void onFailure(Call<ServerResult> call, Throwable t) {

        }
    };

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if(requestCode==2) {
                Uri returnUri = data.getData();
                file = new File(returnUri.getPath());
                InputStream inputStream = null;
                try {
                    inputStream = getContext().getContentResolver().openInputStream(returnUri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                bitmap = BitmapFactory.decodeStream(inputStream);
                byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray());
                uploadFile = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
                modify_profile_image.setImageBitmap(bitmap);
            }
        }
    }
}




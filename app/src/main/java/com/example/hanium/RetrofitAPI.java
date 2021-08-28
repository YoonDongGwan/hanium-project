package com.example.hanium;

import android.media.Image;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("auth/signin")
    public Call<LoginResult> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/temp")
    public Call<LoginResult> find(@Field("email") String email, @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("auth/signup")
    public Call<ConfirmResult> signup(@Field("email") String email, @Field("nickname") String nickname,
                                       @Field("name") String name, @Field("password") String password,
                                       @Field("confirm_pwd") String confirm_pwd, @Field("phoneNumber") String phoneNumber);

    @Multipart
    @POST("post")
    Call<HashMap<String, String>> post(@Header("cookie") String cookie,@Part MultipartBody.Part Image, @PartMap HashMap<String, RequestBody> data);
}

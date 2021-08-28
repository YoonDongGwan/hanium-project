package com.example.hanium;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("auth/signin")
    public Call<LoginResult> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/temp")
    public Call<LoginResult> find(@Field("email") String email, @Field("phoneNumber") String phoneNumber);

    @GET("main")
    public Call<HomePostsResult> getposts(@Header("Cookie")String cookie);

    @GET("user")
    public Call<MypageUserResult> getUser(@Header("Cookie")String cookie);

    @FormUrlEncoded
    @POST("auth/send")
    public Call<NumberResult> send(@Field("email") String email);

    @FormUrlEncoded
    @POST("auth/confirm")
    public Call<ConfirmResult> confirm(@Field("secret") int secret);

    @FormUrlEncoded
    @POST("auth/signup")
    public Call<ConfirmResult> signup(@Field("email") String email, @Field("nickname") String nickname,
                                       @Field("name") String name, @Field("password") String password,
                                       @Field("confirm_pwd") String confirm_pwd, @Field("phoneNumber") String phoneNumber);

}

package com.example.hanium;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("auth/signin")
    public Call<LoginResult> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/temp")
    public Call<LoginResult> find(@Field("email") String email, @Field("phoneNumber") String phoneNumber);
}

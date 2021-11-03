package com.example.hanium.server;


import com.google.gson.JsonObject;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Multipart;

import retrofit2.http.GET;


import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {
    @FormUrlEncoded
    @POST("auth/signin")
    Call<HashMap<String, String>> login(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/temp")
    Call<HashMap<String, String>> find(@Field("email") String email, @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("auth/signup")
    Call<HashMap<String, String>> signup(@Field("email") String email, @Field("nickname") String nickname,
                                       @Field("name") String name, @Field("password") String password,
                                       @Field("confirm_pwd") String confirm_pwd, @Field("phoneNumber") String phoneNumber);

    @Multipart
    @POST("post")
    Call<HashMap<String, String>> post(@Header("cookie") String cookie,@Part MultipartBody.Part Image, @PartMap HashMap<String, RequestBody> data);

    @GET("main")
    Call<HomePostsResult> getPosts(@Header("Cookie")String cookie);

    @GET("user")
    Call<ServerResult> getUser(@Header("Cookie")String cookie);

    @GET("user/edit")
    Call<ServerResult> getModify(@Header("Cookie")String cookie);

    @GET("user/point")
    Call<HashMap<String, String>> getPoint(@Header("Cookie") String cookie);

    @GET("user/logout")
    Call<HashMap<String, String>> logout(@Header("Cookie") String cookie);

    @GET("address")
    Call<ServerResult> getAddress(@Header("Cookie") String cookie);

    @GET("post/detail/{id}")
    Call<PostDetailResult> getDetail(@Path("id") String id, @Header("Cookie") String cookie);

    @GET("post/search")
    Call<HomePostsResult> getSearchResult(@Query("name") String name, @Query("order") String order, @Query("filter") String filter, @Query("page") String page, @Header("Cookie") String cookie);
}

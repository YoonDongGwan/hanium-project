package com.example.hanium.server;


import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @Multipart
    @POST("user/edit")
    Call<HashMap<String, String>> modify(@Header("Cookie") String cookie, @Part MultipartBody.Part Image, @PartMap HashMap<String, RequestBody> data);

    @FormUrlEncoded
    @PUT("user/edit/pwd")
    Call<HashMap<String, String>> changePW(@Header("Cookie") String cookie, @Field("password") String password,
                                           @Field("confirm_pwd") String confirm_pwd, @Field("current_pwd") String current_pwd);

    @POST("user/point/refund")
    Call<HashMap<String, String>> refund(@Header("cookie") String cookie, @Field("refundAmount") int refundAmount, @Field("bankName") String bankName,
                                         @Field("account") String account);

    @FormUrlEncoded
    @POST("user/point/charge")
    Call<HashMap<String, String>> charge(@Header("cookie") String cookie, @Field("chargeAmount") int chargeAmount, @Field("bankName") String bankName,
                                         @Field("account") String account);

    @FormUrlEncoded
    @POST("auth/signup")
    Call<HashMap<String, String>> signup(@Field("email") String email, @Field("nickname") String nickname,
                                         @Field("name") String name, @Field("password") String password,
                                         @Field("confirm_pwd") String confirm_pwd, @Field("phoneNumber") String phoneNumber);

    @FormUrlEncoded
    @POST("auth/send")
    Call<HashMap<String, String>> send(@Field("email") String email);

    @FormUrlEncoded
    @POST("auth/confirm")
    Call<HashMap<String, String>> Confirm(@Header("cookie") String cookie, @Field("email") String email);


    @FormUrlEncoded
    @POST("address/check")
    Call<ServerScope> setScope(@Header("Cookie") String cookie, @Field("addressScope") int addressScope);

    @PUT("address/{districtId}")
    Call<ServerResult> putAddress(@Header("Cookie") String cookie, @Path("districtId") String districtId);

    @Multipart
    @POST("post")
    Call<HashMap<String, String>> addPost(@Header("Cookie") String cookie, @Part("title") RequestBody title, @Part("description") RequestBody description, @Part("price")RequestBody price, @Part("deadline")RequestBody deadline, @Part("requiredTime")RequestBody requiredTime, @Part ArrayList<MultipartBody.Part> Images);

    @Multipart
    @POST("post/edit/{id}")
    Call<HashMap<String, String>> editPost(@Header("Cookie") String cookie, @Path("id")String id, @Part("title") RequestBody title, @Part("description") RequestBody description, @Part("price")RequestBody price, @Part("deadline")RequestBody deadline, @Part("requiredTime")RequestBody requiredTime, @Part ArrayList<MultipartBody.Part> Images);

    @FormUrlEncoded
    @POST("post/review/{id}")
    Call<ServerResult> setReviewPoint(@Header("Cookie") String cookie, @Path("id") String id, @Field("reviewPoint") int reviewPoint);

    @FormUrlEncoded
    @POST("address")
    Call<ServerResult> setAddressScope(@Header("Cookie") String cookie, @Field("addressScope") int addressScope);

    @GET("main")
    Call<HomePostsResult> getPosts(@Header("Cookie") String cookie);

    @GET("user")
    Call<ServerResult> getUser(@Header("Cookie") String cookie);

    @GET("user/edit")
    Call<ServerResult> getModify(@Header("Cookie") String cookie);

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

    @GET("post/history/me")
    Call<HomePostsResult> getMyPosts(@Header("Cookie") String cookie);

    @GET("post/history/all")
    Call<AllHistoryResponse> getAllHistory(@Header("Cookie") String cookie);

    @GET("address/search")
    Call<LocationinfoResult> getAddrSearchResult(@Query("name") String name, @Header("Cookie") String cookie);

    @DELETE("post/{id}")
    Call<ServerResult> deletePost(@Header("Cookie")String cookie, @Path("id")String id);
}

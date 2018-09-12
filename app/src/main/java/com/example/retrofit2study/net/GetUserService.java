package com.example.retrofit2study.net;

import com.example.retrofit2study.model.User;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by 粽子 on 2018/7/9.
 */
/*
 * 只使用retrofit2的网络连接接口
 */

public interface GetUserService {
    //0.简单的get请求 通过get请求一个User 模仿token验证
    @GET("UserServelt")
    Call<User> getUser(@Header("token") String token);

    //1.简单的post请求，通过post请求一个User
    @POST("UserServelt")
    Call<User> getUserByPost(@Header("token") String token);

    //2.传入普通字符串查询
    @GET("QueryServelt")
    Call<User> getUsersBySort(@Query("str") String str);

    //3.传入User的JSON字符串
    @POST("UploadUserServelt")
    Call<User> postUserByJson(@Body User user);

    //4.传入表单类似的键值对
    @POST("TableServelt")
    @FormUrlEncoded
    Call<User> login(@Field("username") String username, @Field("password") String password);

    //5.单文件上传
    @Multipart
    @POST("UploadServelt")
    Call<User> testSingleMultipart(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    //6.多文件上传
    @Multipart
    @POST("UploadServelt")
    Call<User> upload(@PartMap Map<String, RequestBody> params,
                      @Part("password") RequestBody passwrod);

    //7.文件下载 ,先上传再下载确保有东西
    @GET("upload/cxy.txt")
    Call<ResponseBody> downloadTest();


}


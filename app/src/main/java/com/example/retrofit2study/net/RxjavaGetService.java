package com.example.retrofit2study.net;

import com.example.retrofit2study.model.User;

import java.util.Map;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
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
 * Created by 粽子 on 2018/7/13.
 */

public interface RxjavaGetService {
    /*
    *  rxjava 用来替换Call的有两个 Flowable 支持背压  Observable 不支持背压 但正常情况下 开销低一些
     *  根据 选择 使用 .subscribe(new Subscriber<User>(){}) 或 .subscribe(new Observer<User>(){}}
     */
    //0.简单的get请求 通过get请求一个User 模仿token验证
    @GET("UserServelt")
    Flowable<User> getUser(@Header("token") String token);//Flowable支持背压

    //1.简单的post请求，通过post请求一个User
    @POST("UserServelt")
    Observable<User> getUserByPost(@Header("token") String token);

    //2.传入普通字符串查询
    @GET("QueryServelt")
    Observable<User> getUsersBySort(@Query("str") String str);

    //3.传入User的JSON字符串
    @POST("UploadUserServelt")
    Observable<User> postUserByJson(@Body User user);

    //4.传入表单类似的键值对
    @POST("TableServelt")
    @FormUrlEncoded
    Observable<User> login(@Field("username") String username, @Field("password") String password);

    //5.单文件上传
    @Multipart
    @POST("UploadServelt")
    Observable<User> testSingleMultipart(
            @Part("description") RequestBody description,
            @Part MultipartBody.Part file
    );

    //6.多文件上传
    @Multipart
    @POST("UploadServelt")
    Observable<User> upload(@PartMap Map<String, RequestBody> params,
                      @Part("password") RequestBody passwrod);

    //7.文件下载 ,先上传再下载确保有东西
    @GET("upload/cxy.txt")
    Observable<ResponseBody> downloadTest();


}

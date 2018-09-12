package com.example.retrofit2study.net;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Archer on 2018/9/9.
 */

public class Api {
    private static String baseUrl="http://192.168.1.101:8080/Retrofit2Server/";
    /*
     * 同一获得网络请求的接口
     * baserUrl 需按实际情况更改
     */
    public static GetUserService getUserService(){

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GetUserService getUserService=retrofit.create(GetUserService.class);

        return getUserService;
    }
    public static RxjavaGetService RxjavaGetService (){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//Rxjava适配
                .build();
        RxjavaGetService rxjavaGetService=retrofit.create(RxjavaGetService.class);

        return rxjavaGetService;
    }

}

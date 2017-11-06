package com.example.srikant.appathon;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by Srikant on 7/9/2017.
 */

public class ApiClient {

    public static final String BASE_URL = "http://www.numbersapi.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient(){
        if(retrofit==null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

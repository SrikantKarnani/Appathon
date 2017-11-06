package com.example.srikant.appathon;

import java.util.Observable;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.Path;

/**
 * Created by Srikant on 10/9/2017.
 */

public interface ApiInterface {


    @GET("/random/math/")
    Call<String> getRandomMath();

    @GET("random/trivia/")
    Call<String> getRandomTrivia();

    @GET("random/date/")
    Call<String> getRandomDate();

    @GET("random/year/")
    Call<String> getRandomYear();

    @GET("{num}/math/")
    Call<String> getMath(@Path(value = "num",encoded = true) String num);

    @GET("{date}/date/")
    Call<String> getDate(@Path(value = "date",encoded = true) String date);

    @GET("{num}/trivia/")
    Call<String> getTrivia(@Path(value = "num",encoded = true) String num);

    @GET("{num}/year/")
    Call<String> getYear(@Path(value = "num",encoded = true) String num);



}

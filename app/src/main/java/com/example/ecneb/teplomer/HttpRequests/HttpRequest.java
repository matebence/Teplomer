package com.example.ecneb.teplomer.HttpRequests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpRequest {

    private static final String BASE_URL = "http://app-teplomer.8u.cz/index.php/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (HttpRequest.retrofit == null) {
            HttpRequest.retrofit = new Retrofit.Builder().baseUrl(HttpRequest.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return HttpRequest.retrofit;
    }
}
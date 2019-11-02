package com.example.ecneb.teplomer.HttpRequests;

import com.example.ecneb.teplomer.Models.Decrease;
import com.example.ecneb.teplomer.Models.Increase;
import com.example.ecneb.teplomer.Models.Data;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Request {
    @GET("temperatures")
    Call<Data> getTemperature();

    @GET("temperatures/decrease")
    Call<Decrease> decreaseTemperature();

    @GET("temperatures/increase")
    Call<Increase> increaeTemperature();
}
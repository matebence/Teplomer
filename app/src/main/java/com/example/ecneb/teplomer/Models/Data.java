package com.example.ecneb.teplomer.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("temperature")
    private double temperature;

    @SerializedName("humidity")
    private double humidity;

    public Data(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    @NonNull
    @Override
    public String toString() {
        return "Data{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}

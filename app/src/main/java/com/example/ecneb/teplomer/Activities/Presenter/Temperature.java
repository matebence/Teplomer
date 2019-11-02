package com.example.ecneb.teplomer.Activities.Presenter;

import android.support.annotation.NonNull;

public class Temperature {

    private double temperature;
    private double humidity;

    Temperature() {
    }

    public Temperature(double temperature, double humidity) {
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
        return "Temperature{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                '}';
    }
}

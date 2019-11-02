package com.example.ecneb.teplomer.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Decrease {

    @SerializedName("decreased")
    private int decreased;

    public Decrease(int decreased) {
        this.decreased = decreased;
    }

    public int getDecreased() {
        return decreased;
    }

    public void setDecreased(int decreased) {
        this.decreased = decreased;
    }

    @NonNull
    @Override
    public String toString() {
        return "Decrease{" +
                "decreased=" + decreased +
                '}';
    }
}

package com.example.ecneb.teplomer.Models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Increase {

    @SerializedName("increased")
    private int increased;

    public Increase(int increased) {
        this.increased = increased;
    }

    public int getIncreased() {
        return increased;
    }

    public void setIncreased(int increased) {
        this.increased = increased;
    }

    @NonNull
    @Override
    public String toString() {
        return "Increase{" +
                "increased=" + increased +
                '}';
    }
}

package com.example.wizte.ik_test_app.model;

import com.google.gson.annotations.SerializedName;

public class ResponsePhotos {

    @SerializedName("stat")
    private String stat;

    @SerializedName("photos")
    private Photos photos;

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getStat() {
        return stat;
    }

    public void setPhotos(Photos photos) {
        this.photos = photos;
    }

    public Photos getPhotos() {
        return photos;
    }

    @Override
    public String toString() {
        return
                "ResponsePhotos{" +
                        "stat = '" + stat + '\'' +
                        ",photos = '" + photos + '\'' +
                        "}";
    }
}
package com.example.wizte.ik_test_app;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.reactivex.disposables.Disposable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

    private final String BASE_URL = "https://api.flickr.com/";
    private Disposable disposable;

    static FlickrApi flickrApi;

    public static FlickrApi getFlickrApi() {
        return flickrApi;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        flickrApi = retrofit.create(FlickrApi.class);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (disposable != null) {
            disposable.dispose();
        }
    }
}

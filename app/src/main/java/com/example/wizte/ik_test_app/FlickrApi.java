package com.example.wizte.ik_test_app;

import com.example.wizte.ik_test_app.model.ResponsePhotos;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FlickrApi {

    @GET("services/rest/?method=flickr.photos.search&api_key=90f755b7bd47e85fca02039debf076be&extras=url_s%2Cgeo&format=json&nojsoncallback=1")
    Observable<ResponsePhotos> getPhotos(
            @Query("per_page") int per_page,
            @Query("page") int page,
            @Query("text") String text,
            @Query("privacy_filter") int privacy_filter,
            @Query("has_geo") int has_geo,
            @Query("sort") String sort
    );
}
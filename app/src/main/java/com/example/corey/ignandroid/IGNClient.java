package com.example.corey.ignandroid;

import com.example.corey.ignandroid.models.IGNArticlesResponse;
import com.example.corey.ignandroid.models.IGNVideosResponse;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by corey on 5/2/17.
 */

public interface IGNClient {
    @GET("videos")
    Observable<IGNVideosResponse> getVideos(@Query("startIndex") int startIndex, @Query("count") int count);

    @GET("articles")
    Observable<IGNArticlesResponse> getArticles(@Query("startIndex") int startIndex, @Query("count") int count);
}

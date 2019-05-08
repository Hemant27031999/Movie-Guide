package com.example.movieguide;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SearchInterface {
    @GET("search/movie")
    Call<Mainjson> getMainJson(@Query("language") String lang,
                               @Query("query") String query,
                               @Query("include_adult") boolean adult,
                               @Query("page") int num,
                               @Query("api_key") String key);
}

package com.example.movieguide;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IdInterface {

    @GET("movie/{id}/videos")
    Call<MovieId> getMovieId(@Path("id") String id,
                             @Query("api_key") String key);
}

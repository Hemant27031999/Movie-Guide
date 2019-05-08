package com.example.movieguide;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("discover/movie")
    Call<Mainjson> getMainJson(@Query("language") String lang,
                               @Query("sort_by") String order,
                               @Query("include_adult") boolean adult,
                               @Query("include_video") boolean video,
                               @Query("page") int num,
                               @Query("api_key") String key);
}
//language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=0070cb44f64f0c3a551a44955f507e96
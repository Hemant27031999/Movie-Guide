package com.example.movieguide;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.AppCompatTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static com.example.movieguide.MainActivity.mDate;
import static com.example.movieguide.MainActivity.mDescp;
import static com.example.movieguide.MainActivity.mId;
import static com.example.movieguide.MainActivity.mImageUrl;
import static com.example.movieguide.MainActivity.mRate;
import static com.example.movieguide.MainActivity.mTitle;

public class film_indi extends YouTubeBaseActivity {

    private String API_Key = "0070cb44f64f0c3a551a44955f507e96";
    private String youtubeKey;
    YouTubePlayerView youTubePlayerView;
    Button button;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film_indi);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(mImageUrl);
        String title = intent.getStringExtra(mTitle);
        String release_date = intent.getStringExtra(mDate);
        String rate = intent.getStringExtra(mRate);
        String descp = intent.getStringExtra(mDescp);
        String id = intent.getStringExtra(mId);

        ImageView imageView = findViewById(R.id.mainimg);
        AppCompatTextView textViewtitle = findViewById(R.id.title);
        TextView textViewdate = findViewById(R.id.date);
        TextView textViewrate = findViewById(R.id.rate);
        TextView textViewdescp = findViewById(R.id.descp);

        Picasso.with(this).load(imageUrl).into(imageView);
        textViewtitle.setText(title);
        textViewdate.setText(release_date);
        textViewrate.setText(rate);
        textViewdescp.setText(descp);

        IdInterface idservice = ApiClient.getClient().create(IdInterface.class);
        Call<MovieId> call = idservice.getMovieId(id, API_Key);
        call.enqueue(new Callback<MovieId>() {
            @Override
            public void onResponse(Call<MovieId> call, Response<MovieId> response) {
                if(response.body()!=null){
                        List<Result> result= Arrays.asList(response.body().getResult());
                        youtubeKey=result.get(0).getKey();
                }
            }

            @Override
            public void onFailure(Call<MovieId> call, Throwable t) {

            }
        });

        button = (Button) findViewById(R.id.bn);
        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.youtube_player_view);
        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youtubeKey);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                youTubePlayerView.initialize(PlayerConfig.API_KEY,onInitializedListener);
            }
        });

    }
}

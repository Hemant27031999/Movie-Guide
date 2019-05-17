package com.example.movieguide;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements GridAdapter.OnItemClickListener, View.OnClickListener {
    private String API_Key = "0070cb44f64f0c3a551a44955f507e96";
    private String API_Key1 = "ddcb1a9cb8ef80caf814ebb1d3ab511c";
    public TextView textView1;
    public TextView textView2;
    public ImageView imageview1;
    private EditText search;
    private List<Results> results;
    private List<Results> results1;
    private List<Results> searchedresults;
    public static final String mTitle="Title";
    public static final String mDate="Date";
    public static final String mRate="Rate";
    public static final String mDescp="Descp";
    public static final String mImageUrl="ImageUrl";
    public static final String mId="Id";
    private ImageView go;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView1 =(TextView)findViewById(R.id.maintext);
        textView2 =(TextView)findViewById(R.id.maintext1);
        imageview1 =(ImageView) findViewById(R.id.mainimg);
        search=(EditText) findViewById(R.id.searchbox);
        go=(ImageView) findViewById(R.id.go);
        go.setOnClickListener(this);


        final ArrayList<String> imageList = new ArrayList<>();
        textView1.setText("Working Well !!");
        ApiInterface service = ApiClient.getClient().create(ApiInterface.class);
        //language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=0070cb44f64f0c3a551a44955f507e96
        Call<Mainjson> call = service.getMainJson("en-US", "popularity.desc",false, false,1 , API_Key);
        call.enqueue(new Callback<Mainjson>() {
            @Override
            public void onResponse(Call<Mainjson> call, Response<Mainjson> response) {
                results = Arrays.asList(response.body().getResults());
                for(Results myResults : results) {
                    imageList.add("http://image.tmdb.org/t/p/w185/" + myResults.getPoster_path());
                }
                String s=imageList.get(0);
                textView1.setText(s);
                }

            @Override
            public void onFailure(Call<Mainjson> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Unable to retrieve data!",Toast.LENGTH_SHORT).show();
            }
        });

        RecyclerView rv = findViewById(R.id.movie);
        rv.setLayoutManager(new GridLayoutManager(this, 2));
        GridAdapter iga = new GridAdapter(MainActivity.this, imageList, results);
        rv.setAdapter(iga);
        iga.setOnItemClickListener(MainActivity.this);


//        final ArrayList<String> imageList1 = new ArrayList<>();
//        textView2.setText("Working Well !!");
//        ApiInterface service1 = ApiClient.getClient().create(ApiInterface.class);
//        //language=en-US&sort_by=popularity.desc&include_adult=false&include_video=false&page=1&api_key=0070cb44f64f0c3a551a44955f507e96
//        Call<Mainjson> call1 = service1.getMainJson("en-US", "release_date.desc",false, false,1 , API_Key1);
//        call1.enqueue(new Callback<Mainjson>() {
//            @Override
//            public void onResponse(Call<Mainjson> call, Response<Mainjson> response) {
//                results1 = Arrays.asList(response.body().getResults());
//                for(Results myResults : results1) {
//                    imageList1.add("http://image.tmdb.org/t/p/w185/" + myResults.getPoster_path());
//                }
//                String s=imageList.get(0);
//                textView2.setText(s);
//            }
//
//            @Override
//            public void onFailure(Call<Mainjson> call, Throwable t) {
//                Toast.makeText(getApplicationContext(),"Unable to retrieve data!",Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        RecyclerView rv1 = findViewById(R.id.released);
//        rv.setLayoutManager(new GridLayoutManager(this, 2));
//        GridAdapter iga1 = new GridAdapter(MainActivity.this, imageList1, results1);
//        rv1.setAdapter(iga1);
//        iga1.setOnItemClickListener(MainActivity.this);

        imageview1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, film_indi.class);
                startActivity(intent);
            }
        });



    }

    @Override
    public void onItemClick(int position) {
    Intent intent=new Intent(this, film_indi.class);
    Results clickedResult=results.get(position);

    intent.putExtra(mTitle,clickedResult.getTitle());
    intent.putExtra(mDate,clickedResult.getRelease_date());
    intent.putExtra(mRate,clickedResult.getVote_average());
    intent.putExtra(mDescp,clickedResult.getOverview());
    intent.putExtra(mImageUrl,"http://image.tmdb.org/t/p/w185/"+clickedResult.getPoster_path());
    intent.putExtra(mId,clickedResult.getId());

    startActivity(intent);
    }



    @Override
    public void onClick(View v) {
        if(v==go){
            String moviesearched=search.getText().toString().trim();
            if(moviesearched==null){
                Toast.makeText(MainActivity.this, "Please enter a movie name.", Toast.LENGTH_SHORT).show();
            }
            else {
                //https://api.themoviedb.org/3/search/movie?language=en-US&query=avengers&page=1&include_adult=false&api_key=0070cb44f64f0c3a551a44955f507e96
                SearchInterface service = ApiClient.getClient().create(SearchInterface.class);
                Call<Mainjson> call = service.getMainJson("en-US",moviesearched, false,1 , API_Key);
                call.enqueue(new Callback<Mainjson>() {
                    @Override
                    public void onResponse(Call<Mainjson> call, Response<Mainjson> response) {
                        searchedresults = Arrays.asList(response.body().getResults());
                        Intent intent=new Intent(MainActivity.this, film_indi.class);
                        Results finalResult=searchedresults.get(0);

                        intent.putExtra(mTitle,finalResult.getTitle());
                        intent.putExtra(mDate,finalResult.getRelease_date());
                        intent.putExtra(mRate,finalResult.getVote_average());
                        intent.putExtra(mDescp,finalResult.getOverview());
                        intent.putExtra(mImageUrl,"http://image.tmdb.org/t/p/w185/"+finalResult.getPoster_path());
                        intent.putExtra(mId,finalResult.getId());

                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Mainjson> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Request failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }

    }
}
package com.example.movieguide;

public class MovieId {

    private String id;

    private Result[] results;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public Result[] getResult ()
    {
        return results;
    }

    public void setResult (Result[] result)
    {
        this.results = result;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", result = "+results+"]";
    }
}

package com.example.movieguide;

public class Result {
    private String site;

    private String size;

    private String iso_3166_1;

    private String name;

    private String id;

    private String type;

    private String iso_639_1;

    private String key;

    public String getSite ()
    {
        return site;
    }

    public void setSite (String site)
    {
        this.site = site;
    }

    public String getSize ()
    {
        return size;
    }

    public void setSize (String size)
    {
        this.size = size;
    }

    public String getIso_3166_1 ()
    {
        return iso_3166_1;
    }

    public void setIso_3166_1 (String iso_3166_1)
    {
        this.iso_3166_1 = iso_3166_1;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getIso_639_1 ()
    {
        return iso_639_1;
    }

    public void setIso_639_1 (String iso_639_1)
    {
        this.iso_639_1 = iso_639_1;
    }

    public String getKey ()
    {
        return key;
    }

    public void setKey (String key)
    {
        this.key = key;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [site = "+site+", size = "+size+", iso_3166_1 = "+iso_3166_1+", name = "+name+", id = "+id+", type = "+type+", iso_639_1 = "+iso_639_1+", key = "+key+"]";
    }
}

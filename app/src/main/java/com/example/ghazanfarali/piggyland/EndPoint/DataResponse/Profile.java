package com.example.ghazanfarali.piggyland.EndPoint.DataResponse;

/**
 * Created by ghazanfarali on 23/01/2017.
 */

public class Profile {
    private String id;

    private String username;

    private String createdAt;

    private String password;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (String createdAt)
    {
        this.createdAt = createdAt;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", username = "+username+", createdAt = "+createdAt+", password = "+password+"]";
    }
}

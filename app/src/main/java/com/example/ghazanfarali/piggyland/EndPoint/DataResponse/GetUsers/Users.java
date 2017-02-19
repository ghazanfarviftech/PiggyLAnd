package com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUsers;

/**
 * Created by Amir.jehangir on 2/19/2017.
 */
public class Users {
    private String username;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+"]";
    }
}

package com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUserProfile;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class Users {
    private String username;

    private String email;

    private String image;

    private String contact;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getContact ()
    {
        return contact;
    }

    public void setContact (String contact)
    {
        this.contact = contact;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+", email = "+email+", image = "+image+", contact = "+contact+"]";
    }
}

package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean;

/**
 * Created by ghazanfarali on 20/02/2017.
 */

public class MyPojo {

    private Post[] post;

    public Post[] getPost ()
    {
        return post;
    }

    public void setPost (Post[] post)
    {
        this.post = post;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [post = "+post+"]";
    }
}

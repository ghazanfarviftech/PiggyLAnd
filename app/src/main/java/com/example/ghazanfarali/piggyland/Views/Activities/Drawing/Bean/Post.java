package com.example.ghazanfarali.piggyland.Views.Activities.Drawing.Bean;

/**
 * Created by ghazanfarali on 20/02/2017.
 */

public class Post {
    private String id;

    private String title;

    private String description;

    private String likes;

    private String userId;

    private String image;

    private String comments;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getLikes ()
    {
        return likes;
    }

    public void setLikes (String likes)
    {
        this.likes = likes;
    }

    public String getUserId ()
    {
        return userId;
    }

    public void setUserId (String userId)
    {
        this.userId = userId;
    }

    public String getImage ()
    {
        return image;
    }

    public void setImage (String image)
    {
        this.image = image;
    }

    public String getComments ()
    {
        return comments;
    }

    public void setComments (String comments)
    {
        this.comments = comments;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", title = "+title+", description = "+description+", likes = "+likes+", userId = "+userId+", image = "+image+", comments = "+comments+"]";
    }
}

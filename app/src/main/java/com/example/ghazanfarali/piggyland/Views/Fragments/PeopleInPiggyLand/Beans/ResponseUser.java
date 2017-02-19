package com.example.ghazanfarali.piggyland.Views.Fragments.PeopleInPiggyLand.Beans;

/**
 * Created by ghazanfarali on 19/02/2017.
 */

public class ResponseUser {
    User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

class User{

    String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
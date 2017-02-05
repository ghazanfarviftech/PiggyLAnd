package com.example.ghazanfarali.piggyland.EndPoint.DataResponse;

/**
 * Created by ghazanfarali on 23/01/2017.
 */

public class LoginResponse {
    private String status;

    private String auth;

    private Profile profile;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getAuth ()
    {
        return auth;
    }

    public void setAuth (String auth)
    {
        this.auth = auth;
    }

    public Profile getProfile ()
    {
        return profile;
    }

    public void setProfile (Profile profile)
    {
        this.profile = profile;
    }


}

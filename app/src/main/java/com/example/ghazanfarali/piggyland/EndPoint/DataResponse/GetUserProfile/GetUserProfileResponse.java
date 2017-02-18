package com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUserProfile;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class GetUserProfileResponse {
    private Users[] users;

    public Users[] getUsers ()
    {
        return users;
    }

    public void setUsers (Users[] users)
    {
        this.users = users;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [users = "+users+"]";
    }
}

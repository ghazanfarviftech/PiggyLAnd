package com.example.ghazanfarali.piggyland.EndPoint.DataResponse.GetUsers;

/**
 * Created by Amir.jehangir on 2/19/2017.
 */
public class GetUsersResponse {
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

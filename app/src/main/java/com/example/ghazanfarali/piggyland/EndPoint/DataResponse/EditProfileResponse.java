package com.example.ghazanfarali.piggyland.EndPoint.DataResponse;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class EditProfileResponse {
    private String result;

    private String status;

    public String getResult ()
    {
        return result;
    }

    public void setResult (String result)
    {
        this.result = result;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [result = "+result+", status = "+status+"]";
    }

}

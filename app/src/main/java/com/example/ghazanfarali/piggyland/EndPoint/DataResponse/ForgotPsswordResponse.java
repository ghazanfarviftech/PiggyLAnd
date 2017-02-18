package com.example.ghazanfarali.piggyland.EndPoint.DataResponse;

/**
 * Created by Amir.jehangir on 2/18/2017.
 */
public class ForgotPsswordResponse {
    private String status;

    private String msg;

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public String getMsg ()
    {
        return msg;
    }

    public void setMsg (String msg)
    {
        this.msg = msg;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [status = "+status+", msg = "+msg+"]";
    }
}

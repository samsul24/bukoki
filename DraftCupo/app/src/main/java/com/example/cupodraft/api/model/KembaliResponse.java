package com.example.cupodraft.api.model;

public class KembaliResponse {
    private DataKembali[] data;

    private String status;

    public DataKembali[] getData ()
    {
        return data;
    }

    public void setData (DataKembali[] data)
    {
        this.data = data;
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
        return "ClassPojo [data = "+data+", status = "+status+"]";
    }
}

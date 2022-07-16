package com.app.SecondGadgetApp.Status;

public class DataResult<A> extends ResultStatus{

    private A data;

    public DataResult(A data, Integer status)
    {
        super(status);
        this.data = data;
    }
    public DataResult(A data, Integer status, String message)
    {
        super(status, message);
        this.data = data;
    }
    public A getData()
    {
        return this.data;
    }
}

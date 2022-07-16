package com.app.SecondGadgetApp.Status;

public class SuccessResult extends ResultStatus
{
    public SuccessResult(Integer status)
    {
        super(status);
    }
    public SuccessResult(String message)
    {
        super(200, message);
    }
}

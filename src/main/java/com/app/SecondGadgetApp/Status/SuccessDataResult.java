package com.app.SecondGadgetApp.Status;

public class SuccessDataResult<A> extends DataResult<A>
{
    public SuccessDataResult(A data)
    {
        super(data, 200);
    }

    public SuccessDataResult(A data, String message)
    {
        super(data, 200, message);
    }

    public SuccessDataResult()
    {
        super(null, 200);
    }

    public SuccessDataResult(String message)
    {
        super(null, 200, message);
    }
}

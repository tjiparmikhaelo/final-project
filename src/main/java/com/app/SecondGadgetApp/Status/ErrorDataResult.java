package com.app.SecondGadgetApp.Status;

public class ErrorDataResult<A> extends DataResult<A> {
    public ErrorDataResult(A data) {
        super(data, 500);
    }

    public ErrorDataResult(A data, String message){
        super(data, 500, message);
    }

    public ErrorDataResult(){
        super(null, 500);
    }

    public ErrorDataResult(String message){
        super(null, 500, message);
    }
}

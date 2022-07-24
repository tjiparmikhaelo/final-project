package com.app.SecondGadgetApp.Status;

public class ErrorResult extends ResultStatus{

    public ErrorResult(int status, String message){
        super(500);
    }

    public ErrorResult(String message){
        super(500, message, null);
    }
}

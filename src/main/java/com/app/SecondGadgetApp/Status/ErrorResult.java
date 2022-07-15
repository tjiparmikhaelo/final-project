package com.app.SecondGadgetApp.Status;

public class ErrorResult extends ResultStatus{

    public ErrorResult(){
        super(500);
    }

    public ErrorResult(String message){
        super(500, message);
    }


}

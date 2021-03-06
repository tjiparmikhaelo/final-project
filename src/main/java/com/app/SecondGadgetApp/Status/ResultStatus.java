package com.app.SecondGadgetApp.Status;

public class ResultStatus {
    private  Integer status;

    private String message;

    public ResultStatus(Integer status){
        this.status = status;
    }

    public ResultStatus(Integer status, String message){
        this(status);
        this.message = message;
    }

    public Integer isStatus(){
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

}

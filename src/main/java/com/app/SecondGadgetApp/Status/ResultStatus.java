package com.app.SecondGadgetApp.Status;

public class ResultStatus <A>{

    private  Integer status;

    private String message;

    private A data;


    public ResultStatus(Integer status){
        this.status = status;
    }

    public ResultStatus(Integer status, String message, A data){
        this(status);
        this.message = message;
        this.data = data;
    }

    public Integer getStatus(){
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }
}
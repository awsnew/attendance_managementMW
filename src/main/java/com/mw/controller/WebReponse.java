package com.mw.controller;

/**
 *
 * @author lue
 */
public class WebReponse {
    
    private String message;
    private int statusCode;
    private Object responseData="";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getResponseData() {
        return responseData;
    }

    public void setResponseData(Object responseData) {
        this.responseData = responseData;
    }
    
    
    
}

package com.flpss.http;


public class Status {



    public static final int CODE_OK = 200;
    public static final int CODE_NF = 404;
    public static final int CODE_SE = 500;

    public static final String TEXT_OK = "OK";
    public static final String TEXT_NF = "Not Found";
    public static final String TEXT_SE = "Internal Server Error";



    private int code;
    private String text;



    private Status(int code, String text) {
        this.code = code;
        this.text = text;
    }



    public int getCode() {
        return code;
    }

    public String getText() {
        return text;
    }



    public static Status succes() {
        return new Status(
                Status.CODE_OK,
                Status.TEXT_OK
        );
    }



    public static Status notFound() {
        return new Status(
                Status.CODE_NF,
                Status.TEXT_NF
        );
    }
}

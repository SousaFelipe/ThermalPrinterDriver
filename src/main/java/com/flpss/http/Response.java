package com.flpss.http;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;


public class Response {


    private String body;
    private OutputStream output;
    private final Request request;


    public Response(Request request) {
        this.request = request;
    }



    public void setBody(String body) {
        this.body = body;

        HttpExchange exchange = this.request.getExchange();

        try {
            exchange.sendResponseHeaders(Status.CODE_OK, this.body.length());
            this.output = exchange.getResponseBody();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void send() {
        try {
            output.write(this.body.getBytes());
            output.close();

            request.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

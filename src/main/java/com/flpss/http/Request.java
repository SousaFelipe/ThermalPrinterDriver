package com.flpss.http;


import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.InputStream;


public class Request {


    private final InputStream input;
    private final HttpExchange exchange;



    protected Request(HttpExchange exchange) {
        this.exchange = exchange;
        this.input = exchange.getRequestBody();
    }



    public HttpExchange getExchange() {
        return exchange;
    }



    public Response handle(String body) {
        Response response = new Response(this);
        response.setBody(body);
        return response;
    }



    public void close() {
        try {
            this.input.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static Request capture(HttpExchange exchange) {
        return new Request(exchange);
    }
}

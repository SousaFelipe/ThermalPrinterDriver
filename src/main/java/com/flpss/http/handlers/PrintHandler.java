package com.flpss.http.handlers;


import com.flpss.http.Request;
import com.flpss.http.Response;
import com.flpss.printer.Driver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class PrintHandler implements HttpHandler {



    @Override
    public void handle(HttpExchange exchange) {

        Request request = Request.capture(exchange);
        Response response = request.handle("STATIC");
        response.send();
    }
}

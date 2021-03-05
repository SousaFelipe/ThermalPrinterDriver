package com.flpss.http.handlers;


import com.flpss.http.Request;
import com.flpss.http.Response;
import com.flpss.printer.Driver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;


public class StatusHandler implements HttpHandler {



    @Override
    public void handle(HttpExchange exchange) {

        String status = Driver.getStatus();

        Request request = Request.capture(exchange);
        Response response = request.handle(status);
        response.send();
    }
}

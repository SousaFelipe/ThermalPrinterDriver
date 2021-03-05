package com.flpss.http.handlers;


import com.flpss.http.Request;
import com.flpss.http.Response;
import com.flpss.printer.Device;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;


public class StatusHandler implements HttpHandler {



    @Override
    public void handle(HttpExchange exchange) {

        String status = Device.getStatus();

        Request request = Request.capture(exchange);
        Response response = request.handle(status);
        response.send();
    }
}

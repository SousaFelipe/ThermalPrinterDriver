package com.flpss.http.handlers;


import com.flpss.http.Request;
import com.flpss.http.Response;
import com.flpss.printer.Driver;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.util.List;


public class StatusHandler implements HttpHandler {



    @Override
    public void handle(HttpExchange exchange) {

        String devices = "";

        for (String device : Driver.getPrinterNames()) {
            devices = devices.concat("> " + device + "\n");
        }

        Request request = Request.capture(exchange);
        Response response = request.handle(devices);
        response.send();
    }
}

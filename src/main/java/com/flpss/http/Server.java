package com.flpss.http;


import com.flpss.http.handlers.PrintHandler;
import com.flpss.http.handlers.StatusHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;


public class Server {



    private HttpServer httpServer;



    public Server(int port) {
        try {

            this.httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            this.httpServer.createContext("/status", new StatusHandler());
            this.httpServer.createContext("/print", new PrintHandler());
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void start() {
        this.httpServer.setExecutor(null);
        this.httpServer.start();
    }


    public void stop() {
        this.httpServer.stop(0);
    }
}

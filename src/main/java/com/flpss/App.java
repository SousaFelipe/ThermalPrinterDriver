package com.flpss;


import com.flpss.http.Server;


public class App {



    public static void main(String[] args)
    {
        Server server = new Server(8888);
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
    }
}

package com.httpserver;

import com.httpserver.handler.HttpServer;

public class Server {
    public static void main(String[] args) {
        try {
            new HttpServer().Start(8080);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.mdtanvirhossain.jtp.ch6.executorframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadedServer {
    public static void main(String[] args) throws IOException {
        SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket connection = serverSocket.accept();

            Thread thread = new Thread(() -> {
                singleThreadedServer.serverRequest(connection);
            });

            thread.start();
        }
    }
}

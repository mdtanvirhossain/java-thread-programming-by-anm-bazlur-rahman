package com.mdtanvirhossain.jtp.ch6.executorframework;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MultiThreadedServer2 {
    public static void main(String[] args) throws IOException {
        SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
        ServerSocket serverSocket = new ServerSocket(8080);

        Executor executor = Executors.newFixedThreadPool(10);

        while (true) {
            Socket connection = serverSocket.accept();

            /*executor.execute(new Runnable() {
                @Override
                public void run() {
                    singleThreadedServer.serverRequest(connection);
                }
            });*/

            executor.execute(() -> singleThreadedServer.serverRequest(connection));
        }
    }
}

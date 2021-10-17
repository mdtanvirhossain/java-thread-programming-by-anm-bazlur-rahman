package com.mdtanvirhossain.jtp.ch6.executorframework;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadedServer {

    public static final String DOCUMENT = "" +
            "<html>" +
            "   <head>" +
            "       <title>Single Threaded Server</title>" +
            "   </head>" +
            "   <body>" +
            "       <p>It works!</p>" +
            "   </body>" +
            "</html>";

    public static final String HEADER =
            "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: text/html; charset=UFT-8\r\n" +
                    "Content-Length: " + DOCUMENT.length() +
                    "\r\n\r\n";

    public void serverRequest(Socket connection) {
        System.out.println("New connection request from: " + connection.toString());

        try (OutputStream os = connection.getOutputStream();
             PrintWriter out = new PrintWriter(os)) {

            out.write(HEADER);
            out.write(DOCUMENT);

        } catch (IOException e) {
            Thread.currentThread().interrupt();
            throw new AssertionError(e);
        }
    }

    public static void main(String[] args) throws IOException {
        SingleThreadedServer singleThreadedServer = new SingleThreadedServer();
        ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            Socket connection = serverSocket.accept();
            singleThreadedServer.serverRequest(connection);
        }
    }

}

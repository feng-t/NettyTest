package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class SocketTest {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));
        ssc.configureBlocking(true);
        while (true) {
            SocketChannel accept = ssc.accept();

            System.out.println("running...");
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            String msg = "HTTP/1.1 200 File Not Found\r\n" +
                    "Content-Type: text/html\r\n" +
                    "Content-Length: 23\r\n" +
                    "\r\n" +
                    "<h1> 404 File Not Found </h1>";
            buffer.put(msg.getBytes());
            buffer.flip();
            accept.write(buffer);
            buffer.clear();

        }
    }
}

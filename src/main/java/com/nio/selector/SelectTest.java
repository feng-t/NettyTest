package com.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;

public class SelectTest {
    private static int port = 6789;

    public static void main(String[] args) throws Exception {
        new SelectTest().go();
    }

    private void go() throws Exception {
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        ServerSocket socket = socketChannel.socket();
        Selector selector = Selector.open();
        socket.bind(new InetSocketAddress(port));
        socketChannel.configureBlocking(false);
        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            int n = selector.select();
            if (n != 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel channel = server.accept();
                        registerChannel(selector, channel, SelectionKey.OP_READ);
                        sayHello(channel);
                    }
                    if (key.isReadable()) {
                        readDataFromSocket(key);
                    }
                    iterator.remove();
                }
            }
        }

    }
    private ByteBuffer buffer=ByteBuffer.allocateDirect(1024);
    private void readDataFromSocket(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        int count;
        buffer.clear();
        while ((count=socketChannel.read(buffer) )>0){
            buffer.flip();
            while (buffer.hasRemaining()){
                socketChannel.write(buffer);
            }
            buffer.clear();
        }
        if (count<0){
            socketChannel.close();
        }
    }

    private void registerChannel(Selector selector, SelectableChannel channel, int ops) throws IOException {
        if(channel==null){
            return;
        }
        channel.configureBlocking(false);
        channel.register(selector,ops);

    }
    private void sayHello(SocketChannel channel) throws IOException {
        buffer.clear();
        buffer.put("hello".getBytes());
        buffer.flip();
        channel.write(buffer);
    }
}

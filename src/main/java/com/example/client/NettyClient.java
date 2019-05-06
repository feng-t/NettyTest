package com.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @program: nettyTest
 * @description:
 * @author: feng
 * @create: 2019-05-01 22:31
 **/
public class NettyClient {


    public static void main(String[] args) throws Exception {
        NioEventLoopGroup loopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(loopGroup).channel(NioSocketChannel.class).
                    handler(new ClientInitializer());

            Channel channel = bootstrap.connect("localhost", 8888).channel();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for (; ; ) {
                String line = reader.readLine();
                if (line.equals("exit")) {
                    channel.closeFuture();
                    break;
                } else
                    channel.writeAndFlush(line);
            }

        } finally {
            loopGroup.shutdownGracefully();
        }
    }


}

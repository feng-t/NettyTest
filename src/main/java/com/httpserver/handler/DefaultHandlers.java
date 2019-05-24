package com.httpserver.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class DefaultHandlers extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //初始化器
        ch.pipeline()
                .addLast(new HttpServerCodec())
                .addLast(new HttpHandler());
    }
}

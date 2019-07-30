package com.httpserver.handler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpServer {

    public HttpServer Start(int port) throws InterruptedException {

        Start(port, new DefaultHandlers());
        return this;
    }

    /**
     * @param port
     * @param childHandlers
     * @throws InterruptedException
     */
    public HttpServer Start(int port, ChannelHandler... childHandlers) throws InterruptedException {
        //接收连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理连接，真正对连接的处理
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ServerBootstrap channel = bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class);
            for (ChannelHandler handler : childHandlers) {
                channel.childHandler(handler);
            }
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        return this;
    }

}

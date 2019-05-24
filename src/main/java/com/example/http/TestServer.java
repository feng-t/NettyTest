package com.example.http;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class TestServer {

    public static void main(String[] args) throws Exception {
        //接收连接
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //处理连接，真正对连接的处理
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(new TestServerInitalizer());

            ChannelFuture sync = bootstrap.bind(9989).sync().channel().closeFuture().sync();
            //sync.channel().closeFuture().sync();

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

package com.nettytomcat;

import com.nettytomcat.handler.DefaultHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class NettyServer {

    private boolean shutdown = false;

    public static void main(String[] args) {
        new NettyServer().await(9900,new DefaultHandler());
    }

    private void await(int port, ChannelHandler... channelHandlers) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup wokerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            ServerBootstrap channel = bootstrap.group(bossGroup, wokerGroup).channel(NioServerSocketChannel.class);
            for (ChannelHandler handler : channelHandlers) {
                channel.childHandler(handler);
            }
            bootstrap.bind(port).sync().channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }

}

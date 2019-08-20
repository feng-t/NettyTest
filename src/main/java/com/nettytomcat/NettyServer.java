package com.nettytomcat;

import io.netty.channel.nio.NioEventLoopGroup;

import java.io.File;

public class NettyServer {
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "WEB_ROOT";
    private static final String STOP = "/STOP";
    private boolean shutdown = false;

    public static void main(String[] args) {

    }

    public void await(int port) {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup wokerGroup = new NioEventLoopGroup();
        try{

        }catch (Exception e){
            bossGroup.shutdownGracefully();
            wokerGroup.shutdownGracefully();
        }
    }

}

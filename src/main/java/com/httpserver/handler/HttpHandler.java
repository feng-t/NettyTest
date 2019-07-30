package com.httpserver.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.HttpObject;

public class HttpHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * 处理连接
     *
     * @param ch
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ch, HttpObject msg) throws Exception {
        System.out.println(msg);
        ch.writeAndFlush("ddd");
    }
}

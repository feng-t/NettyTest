package com.example.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @program: nettyTest
 * @description:
 * @author: feng
 * @create: 2019-05-01 22:40
 **/
public class ClientHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println(msg);
        if (msg.startsWith("exit")) {
            System.out.println("退出----");
            ctx.close();
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.channel().writeAndFlush("start");
    }


    /**
     * 异常关闭状态
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("关闭连接");
        ctx.close();
    }
}

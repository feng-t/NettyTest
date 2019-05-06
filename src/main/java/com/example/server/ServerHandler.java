package com.example.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @program: nettyTest
 * @description:
 * @author: feng
 * @create: 2019-05-01 21:52
 **/


public class ServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        final Channel channel = ctx.channel();
//        if (msg.equals("ss:")) {
//            System.out.println("----");
//            if (channel == channelGroup.iterator().next())
//                channelGroup.iterator().next().writeAndFlush("发给第一个的消息");
//
//        }
        channelGroup.forEach(ch -> {
            System.out.println("xxx");
            if (ch == channel) {
                ch.writeAndFlush("【自己】 : " + msg);
            } else {
                ch.writeAndFlush(channel.remoteAddress() + " : " + msg);
            }

        });
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[服务器] - " + channel.remoteAddress() + " 加入");
        channelGroup.add(channel);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("[服务器] - " + ctx.channel().remoteAddress() + " 离开");
    }

    /**
     * 表示channel处于活动状态
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        System.out.println(channel.remoteAddress() + " 下线");
    }

    /**
     * 出现异常调用
     *
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

        ctx.close();
    }
}

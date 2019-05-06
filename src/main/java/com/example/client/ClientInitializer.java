package com.example.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

/**
 * @program: nettyTest
 * @description:
 * @author: feng
 * @create: 2019-05-01 22:37
 **/
public class ClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4)).
                addLast(new LengthFieldPrepender(4)).
                addLast(new StringDecoder(CharsetUtil.UTF_8)).
                addLast(new StringEncoder(CharsetUtil.UTF_8)).
                addLast(new ClientHandler());
    }
}

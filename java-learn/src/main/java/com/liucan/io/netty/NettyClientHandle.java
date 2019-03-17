package com.liucan.io.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.FileInputStream;

/**
 * @author liucan
 * @version 19-3-17
 */
public class NettyClientHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("客户端收到处理");
        ByteBuf result = (ByteBuf) msg;
        byte[] result1 = new byte[result.readableBytes()];
        result.readBytes(result1);
        System.out.println("收到数据:" + result1 + "收到长度: " + result1.length);
        result.release();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // 当出现异常就关闭连接
        cause.printStackTrace();
        ctx.close();
    }


    // 连接成功后，向server发送消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        FileInputStream fileInputStream = new FileInputStream("D:\\Java_api.rar");
        byte[] bytes = new byte[1024 * 8];
        int len = -1;
        while ((len = fileInputStream.read(bytes)) != -1) {
            ByteBuf encoded = ctx.alloc().buffer(len);
            encoded.writeBytes(bytes, 0, len);
            ctx.write(encoded);
            ctx.flush();
        }

    }
}

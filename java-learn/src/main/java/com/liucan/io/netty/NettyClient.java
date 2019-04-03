package com.liucan.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author liucan
 * @version 19-3-17
 */
public class NettyClient {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            new Bootstrap()
                    .group(workerGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new NettyClientHandle());
                        }
                    })
                    .connect(host, port)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            workerGroup.shutdownGracefully();
            System.out.println("连接关闭等异常");
        }
    }

    public static void main(String[] args) throws Exception {
        NettyClient nettyClient = new NettyClient();
        nettyClient.connect("127.0.0.1", 8081);
    }
}

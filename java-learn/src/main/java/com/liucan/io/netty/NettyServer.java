package com.liucan.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author liucan
 * @version 19-3-17
 */
public class NettyServer {
    public int maxSubArray(int[] nums) {
        int sum=nums[0];
        int n=nums[0];
        for(int i=1;i<nums.length;i++) {
            if(n>0) {
                n+=nums[i];
            } else {
                n=nums[i];
            }
            if(sum<n) {
                sum=n;
            }
        }
        return sum;
    }
    public void run() throws Exception {
        //bossGroup 用来接收进来的连接
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //workerGroup 用来处理已经被接收的连接
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            //启动 NIO 服务的辅助启动类
            new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast(new NettyServerHandle());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .bind(8081)
                    .sync()
                    .channel()
                    .closeFuture()
                    .sync();
        } finally {
            // 出现异常终止
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            System.out.println("连接关闭等异常");
        }
    }

    public static void main(String[] args) throws Exception {
        new NettyServer().run();
    }
}

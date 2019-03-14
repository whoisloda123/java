package com.liucan.io;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * nio学习参考：http://ifeve.com/java-nio-channel-to-channel/
 * <p>
 * Channel通道，相等于io里面的stream，但是不同的是可以支持写入和读取
 * Buffer内容载体，可以写入通道或者通道里面读取内容
 *
 * @author liucan
 * @version 19-3-14
 */
@Component
public class Nio1 {

    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File("/tmp/test"), "rw");
            FileChannel channel = randomAccessFile.getChannel();

            //写到byteBuffer
            byteBuffer.clear(); //将buffer切换到写模式,position设为0,limit设为capacity
            byteBuffer.compact(); //将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面
            channel.read(byteBuffer);
            byteBuffer.put((byte) 1);

            //读取到channel
            byteBuffer.flip(); //将buffer切换到读模式，position设为0，limit设为之前写的position，表示最多能都之前写的位置
            channel.write(byteBuffer);
            byte b = byteBuffer.get();

            //聚合和分散,经常用于需要将传输的数据分开处理的场合，如传输消息头和消息体组成的消息，分散到不同的buffer中方便处理
            ByteBuffer headBuffer = ByteBuffer.allocate(2);
            ByteBuffer bodyBuffer = ByteBuffer.allocate(4);
            ByteBuffer[] msgBuffer = {headBuffer, bodyBuffer};
            channel.read(msgBuffer);
            channel.write(msgBuffer);

            Selector selector = Selector.open();
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_CONNECT)
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

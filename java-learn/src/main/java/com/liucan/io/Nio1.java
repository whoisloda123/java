package com.liucan.io;

import com.liucan.pojo.Person;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * nio学习参考：http://ifeve.com/java-nio-channel-to-channel/
 * <p>
 * Channel通道，相等于io里面的stream，但是不同的是可以支持写入和读取
 * Buffer内容载体，可以写入通道或者通道里面读取内容
 * Selector相对于linux的select和epoll异步网络io模型，只需要一个线程就可以处理很多网络io
 *
 * @author liucan
 * @version 19-3-14
 */
@Component
public class Nio1 {

    private ExecutorService threadPool = Executors.newFixedThreadPool(200);

    private void serviceSocketChannel() {
        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);

            new Thread(() -> {
                while (true) {
                    try {
                        SocketChannel accept = serverSocketChannel.accept();
                        if (accept != null) {
                            ByteBuffer allocate = ByteBuffer.allocate(1024);
                            allocate.clear();
                            accept.read(allocate);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (Exception e) {

        }
    }

    //udp
    private void datagramChannel() {
        try (DatagramChannel datagramChannel = DatagramChannel.open()) {
            datagramChannel.socket().bind(new InetSocketAddress(9999));
            //接收数据
            ByteBuffer allocate = ByteBuffer.allocate(1024);
            allocate.clear();
            datagramChannel.receive(allocate);

            //发送数据
            allocate = ByteBuffer.allocate(1024);
            allocate.clear();
            allocate.put("sfsfsfs".getBytes());
            allocate.flip();
            datagramChannel.send(allocate, new InetSocketAddress("www.baidu.com", 8080));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //tcp
    private void socketChannel() {
        try (Selector selector = Selector.open();
             SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8848))) {
            socketChannel.register(selector,
                    SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

            new Thread(() -> {
                while (true) {
                    try {
                        //等待注册的通道事件准备就绪
                        int select = selector.select();
                        if (select == 0) {
                            continue;
                        }
                        Set<SelectionKey> selectionKeys = selector.selectedKeys();
                        Iterator<SelectionKey> iterator = selectionKeys.iterator();
                        while (iterator.hasNext()) {
                            SelectionKey selectionKey = iterator.next();
                            threadPool.submit(() -> {
                                try {
                                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                                    if (selectionKey.isConnectable()) { //连接就绪
                                    } else if (selectionKey.isAcceptable()) { //
                                    } else if (selectionKey.isReadable()) {
                                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                                        channel.read(allocate);
                                    } else if (selectionKey.isWritable()) {
                                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                                        channel.write(allocate);
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                            iterator.remove();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fileChannel() {
        File file = new File("/tmp/test");
        try (FileInputStream fileInputStream = new FileInputStream(file);
             FileChannel channel = fileInputStream.getChannel()) {
            //从fileChannel中读取数据
            ByteBuffer allocate = ByteBuffer.allocate(50);
            channel.read(allocate);

            //向fileChannel写数据
            allocate.clear();
            allocate.put("你哈哦啊！".getBytes());
            allocate.flip();

            //注意FileChannel.write()是在while循环中调用的。因为无法保证write()方法一次能向FileChannel写入多少字节，
            // 因此需要重复调用write()方法，直到Buffer中已经没有尚未写入通道的字节
            while (allocate.hasRemaining()) {
                channel.write(allocate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void test() {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(new File("/tmp/test"), "rw");
            FileChannel channel = randomAccessFile.getChannel();

            //写到byteBuffer
            byteBuffer.clear(); //position设为0,limit设为capacity
            byteBuffer.compact(); //将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面
            channel.read(byteBuffer);
            byteBuffer.put((byte) 1);

            //读取到channel
            byteBuffer.flip(); //，position设为0，limit设为之前写的position，表示最多能都之前写的位置
            channel.write(byteBuffer);
            byte b = byteBuffer.get();

            //聚合和分散,经常用于需要将传输的数据分开处理的场合，如传输消息头和消息体组成的消息，分散到不同的buffer中方便处理
            ByteBuffer headBuffer = ByteBuffer.allocate(2);
            ByteBuffer bodyBuffer = ByteBuffer.allocate(4);
            ByteBuffer[] msgBuffer = {headBuffer, bodyBuffer};
            channel.read(msgBuffer);
            channel.write(msgBuffer);

            //selector,与Selector一起使用时，Channel必须处于非阻塞模式下。这意味着不能将FileChannel与Selector一起使用，
            // 因为FileChannel不能切换到非阻塞模式。而套接字通道都可以
            Selector selector = Selector.open();
            SelectableChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            SelectionKey selectionKey = socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT);

            //感兴趣集合
            int interestOps = selectionKey.interestOps();
            int isOpAccept = interestOps & SelectionKey.OP_ACCEPT;

            //已经就绪集合
            int readyOps = selectionKey.readyOps();
            boolean acceptable = selectionKey.isAcceptable();

            //访问selector和channel
            selector = selectionKey.selector();
            socketChannel = selectionKey.channel();

            //attach 对象
            Person person = new Person();
            selectionKey.attach(person);
            person = (Person) selectionKey.attachment();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

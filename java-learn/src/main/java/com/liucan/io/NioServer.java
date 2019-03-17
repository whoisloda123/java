package com.liucan.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * https://www.jianshu.com/p/e6f18c74bc3b
 * @author liucan
 * @version 19-3-17
 */
public class NioServer implements Runnable {

    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NioServer(int port) throws IOException {
        selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector,
                SelectionKey.OP_CONNECT | SelectionKey.OP_ACCEPT | SelectionKey.OP_READ | SelectionKey.OP_WRITE);

        System.out.println("Server start, port：" + port);
    }

    private void accept(SelectionKey key) {
        ServerSocketChannel socketChannel = (ServerSocketChannel) key.channel();
        try {
            //阻塞等待新client连接
            SocketChannel accept = socketChannel.accept();
            accept.configureBlocking(false);
            //注册到多路复用选择器上，并设置读取标识
            accept.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void read(SelectionKey key) {
        //清空缓冲区中的旧数据
        buffer.clear();
        //获取之前注册的SocketChannel通道
        SocketChannel sc = (SocketChannel) key.channel();
        try {
            int count = sc.read(buffer);
            if (count == -1) {
                key.channel().close();
                key.cancel();
                return;
            }
            //读取到了数据，将buffer的position复位到0
            buffer.flip();
            byte[] bytes = new byte[buffer.remaining()];

            //将buffer中的数据写入byte[]中
            buffer.get(bytes);
            System.out.println("Server：" + new String(bytes).trim());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                int select = selector.select();
                if (select == 0) {
                    continue;
                }
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isValid()) {
                        //accept准备就绪
                        if (key.isAcceptable()) {
                            accept(key);
                        } else if (key.isReadable()) {
                            read(key);
                        }
                    }
                    iterator.remove();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.liucan.io;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;

/**
 * 1.File是io流的基础
 * 2.RandomAccessFile类支持"随机访问"方式，可以跳转到文件的任意位置处读写数据
 * 3.流式输入/输出是建立在四个抽象类的基础上的：InputStream、OutputStream、Reader、Writer
 *      InputStream和OutputStream为字节流设计，Reader和Writer为字符流设计
 *      字节流和字符流形成分离的层次结构。一般来说，处理字符或字符串使用字符流类，处理字节或二进制对象使用字节流
 *      字符流还是字节流，都可以按照以下方式进行
 *          a.使用File类找到一个对象
 *          b.通过File类的对象去实例化字节流或字符流的子类
 *          c.进行字节（字符）的读、写操作
 *          d.关闭文件流
 * 4.字符流和字节流的区别
 *  参考：https://www.cnblogs.com/szlbm/p/5513125.html
 *  a.字节流直接操作文件本身，而字符流操作使用缓冲区，然后通过缓冲区（内存）操作文件，使用缓冲区的原因是提高性能
 *  b.在字符流操作中都是使用的缓冲区，输出前会将所有的内容暂时保存在内存之中，使用Writer的flush()方法马上将缓冲区内容写入文件，而不用等到close
 *  c.字符流本身是一种特殊的字节流，字节流和字符流的转换是以InputStreamReader和 OutputStreamWriter为媒介的，InputStreamReader可以将一个字节流中的字节解码成字符，
 *      OutputStreamWriter可以将写入的字符编码成自节后写入一个字节流。
 *  d.避免频繁地进行字符与字节之间的相互转换，最好不要直接使用FileReader和FileWriter这两个类进行读写
 *      而使用BufferedWriter包装OutputStreamWriter，使用BufferedReader包装InputStreamReader
 *
 * 5.io简单总结
 *  https://www.cnblogs.com/szlbm/p/5513147.html
 *
 * @author liucan
 * @version 19-3-10
 */
@Component
public class Io1 {

    public void test() {
        //File是io流的基础
        File file = new File("/home/liucan/Downloads/会员周签到翻牌活动.docx");
        if (file.exists()) {
            System.out.println("file:" + file.getAbsolutePath() + "is exists");
        }

        //RandomAccessFile类支持"随机访问"方式，可以跳转到文件的任意位置处读写数据
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "r")) {
            byte[] bytes = new byte[5];
            randomAccessFile.read(bytes, 0, bytes.length);
            System.out.println(new String(bytes, Charset.defaultCharset()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new Byte1().test();
        new Char1().test();
    }
}

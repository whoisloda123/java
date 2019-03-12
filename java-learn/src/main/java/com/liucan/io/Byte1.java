package com.liucan.io;

import com.liucan.pojo.Country;

import java.io.*;
import java.nio.charset.Charset;

/**
 * inputStream和OutputStream用于字节流
 * 字节流，常用的是文件字节流FileOutputStream,FileInputStream,对象字节流ObjectOutputStream,ObjectInputStream
 *
 * @author liucan
 * @version 19-3-12
 */
public class Byte1 {

    public void test() {
        File file = new File("/tmp/test");

        //文件流
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write("你好啊！".getBytes());
            fileOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[100];
            int read = fileInputStream.read(bytes);
            System.out.println("读取到文件：" + file.getPath() + ",长度：" + read + "内容："
                    + new String(bytes, 0, read, Charset.defaultCharset()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //管道流:用于连接两个线程的通信
        try (PipedOutputStream pipedOutputStream = new PipedOutputStream();
             PipedInputStream pipedInputStream = new PipedInputStream()) {
            pipedInputStream.connect(pipedOutputStream);
            pipedOutputStream.write(1);
            int read = pipedInputStream.read();
            System.out.println("read:" + read);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //对象流(序列化)
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            Country country = new Country();
            country.setAge(1);
            country.setName("123");
            country.setPeople(2L);
            objectOutputStream.writeObject(country);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Country country = (Country) objectInputStream.readObject();
            System.out.println("读取到对象：" + country);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

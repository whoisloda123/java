package com.liucan.io;

import java.io.*;

/**
 * Reader和Writer用于字符流
 * 字符流一般不会用FileReader,FileWriter,而是BufferedWriter，BufferedReader,提高性能
 * 在字符流操作中都是使用的缓冲区
 * 字节流和字符流的转换是以InputStreamReader和 OutputStreamWriter
 *
 * @author liucan
 * @version 19-3-12
 */
public class Char1 {

    public void test() {
        File file = new File("/tmp/test");
        try (FileWriter fileWriter = new FileWriter(file);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write("哈哈！");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            String s = bufferedReader.readLine();
            System.out.println("读取到文件：" + file.getPath() + ",长度：" + s.length() + "内容：" + s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.liucan.util;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1.提供object对象的序列化和反序列化接口
 * 2.若想手动序列化，序列化类只需要实现writeObject和readObject方法，且继承java.io.Serializable接口
 *
 * @author liucan
 * @version 18-11-17
 */
@Component
public class SerializeUtil {

    /**
     * 对象序列化
     */
    @SuppressWarnings("unchecked")
    public static byte[] objectSerializer(Object object) throws IOException {
        if (object == null) {
            return null;
        }
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(object);
            return byteArrayOutputStream.toByteArray();
        }
    }

    /**
     * 对象反序列化
     */
    @SuppressWarnings("unchecked")
    public static Object objectDeserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        }
    }

    /**
     * list对象序列化
     */
    @SuppressWarnings("unchecked")
    public static byte[] listSerialize(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            List<Object> objects = (List<Object>) obj;
            for (Object object : objects) {
                objectOutputStream.writeObject(object);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    /**
     * list对象反序列化
     */
    @SuppressWarnings("unchecked")
    public static List<Object> listDeserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        if (bytes == null) {
            return null;
        }
        List<Object> list = new ArrayList<>();
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            while (objectInputStream.available() > 0) {
                Object object = objectInputStream.readObject();
                if (object == null) {
                    break;
                }
                list.add(object);
            }
            return list;
        }
    }
}

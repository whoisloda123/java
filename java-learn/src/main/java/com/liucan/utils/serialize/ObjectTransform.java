package com.liucan.utils.serialize;

import java.io.*;

/**
 * 1.提供object对象的序列化和反序列化接口
 * 2.若想手动序列化，序列化类只需要实现writeObject和readObject方法，且继承java.io.Serializable接口
 */
public class ObjectTransform implements ISerializeTransform {
    @SuppressWarnings("unchecked")
    @Override
    public byte[] serialize(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(obj);
            return byteArrayOutputStream.toByteArray();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
            return objectInputStream.readObject();
        }
    }
}

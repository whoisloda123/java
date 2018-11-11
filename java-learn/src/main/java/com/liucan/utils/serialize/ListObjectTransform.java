package com.liucan.utils.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListObjectTransform implements ISerializeTransform {
    @SuppressWarnings("unchecked")
    @Override
    public byte[] serialize(Object obj) throws IOException {
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

    @SuppressWarnings("unchecked")
    public List<Object> deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
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

package com.liucan.utils.serialize;

import lombok.extern.slf4j.Slf4j;

import java.io.*;

@Slf4j
public class ObjectsTranscoder extends SerializeTranscoder {
    private static ObjectsTranscoder objectsTranscoder = new ObjectsTranscoder();

    public static ObjectsTranscoder getObjectsTranscoder() {
        return objectsTranscoder;
    }

    //obj必须要实现java.io.Serializable接口
    @SuppressWarnings("unchecked")
    @Override
    public byte[] serialize(Object obj) {
        byte[] result = null;
        if (obj != null) {
            try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
                 ObjectOutputStream os = new ObjectOutputStream(bos)) {
                os.writeObject(obj);
                result = bos.toByteArray();
            } catch (Exception e) {
                throw new IllegalArgumentException("Non-serializable object", e);
            }
        }
        return result;
    }

    //obj必须要实现java.io.Serializable接口
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(byte[] bytes) {
        Object result = null;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream is = new ObjectInputStream(bis)) {
            result = is.readObject();
        } catch (IOException e) {
            log.error(String.format("[序列化]Caught IOException decoding %d bytes of data", bytes.length) + e);
        } catch (ClassNotFoundException e) {
            log.error(String.format("[序列化]Caught CNFE decoding %d bytes of data", bytes.length) + e);
        }
        return result;
    }
}

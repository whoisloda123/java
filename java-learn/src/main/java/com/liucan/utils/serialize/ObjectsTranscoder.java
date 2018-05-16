package com.liucan.utils.serialize;

import java.io.*;

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
            ByteArrayOutputStream bos = null;
            ObjectOutputStream os = null;

            try {
                bos = new ByteArrayOutputStream();
                os = new ObjectOutputStream(bos);
                os.writeObject(obj);
                os.close();
                bos.close();
                result = bos.toByteArray();
            } catch (IOException e) {
                throw new IllegalArgumentException("Non-serializable object", e);
            } finally {
                close(os);
                close(bos);
            }
        }
        return result;
    }

    //obj必须要实现java.io.Serializable接口
    @SuppressWarnings("unchecked")
    @Override
    public Object deserialize(byte[] bytes) {
        Object result = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (bytes != null) {
                bis = new ByteArrayInputStream(bytes);
                is = new ObjectInputStream(bis);
                result = is.readObject();
                is.close();
                bis.close();
            }
        } catch (IOException e) {
            logger.error(String.format("Caught IOException decoding %d bytes of data", bytes.length) + e);
        } catch (ClassNotFoundException e) {
            logger.error(String.format("Caught CNFE decoding %d bytes of data", bytes.length) + e);
        } finally {
            close(is);
            close(bis);
        }
        return result;
    }
}

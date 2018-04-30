package com.liucan.utils.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListTranscoder extends SerializeTranscoder {
    private static ListTranscoder listTranscoder = new ListTranscoder();

    public static ListTranscoder getListTranscoder() {
        return listTranscoder;
    }

    //obj必须要实现java.io.Serializable接口
    @SuppressWarnings("unchecked")
    @Override
    public byte[] serialize(Object obj) {
        byte[] results = null;
        if (obj != null) {
            List<Object> values = (List<Object>) obj;
            ByteArrayOutputStream bos = null;
            ObjectOutputStream os = null;
            try {
                bos = new ByteArrayOutputStream();
                os = new ObjectOutputStream(bos);
                for (Object m : values) {
                    os.writeObject(m);
                }
                // os.writeObject(null);
                os.close();
                bos.close();
                results = bos.toByteArray();
            } catch (IOException e) {
                throw new IllegalArgumentException("Non-serializable object", e);
            } finally {
                close(os);
                close(bos);
            }
        }
        return results;
    }

    //obj必须要实现java.io.Serializable接口
    @SuppressWarnings("unchecked")
    public List<Object> deserialize(byte[] bytes) {
        List<Object> list = new ArrayList<>();
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (bytes != null) {
                bis = new ByteArrayInputStream(bytes);
                is = new ObjectInputStream(bis);
                while (bis.available() > 0) {
                    // while(true) will throw EOFException
                    Object m = is.readObject();
                    if (m == null) {
                        break;
                    }
                    list.add(m);
                }
                is.close();
                bis.close();
            }
        } catch (IOException e) {
            logger.error(String.format("Caught IOException decoding %d bytes of data", bytes.length) + e);
        } catch (ClassNotFoundException e) {
            logger.error(String.format("Caught CNFE decoding %d bytes of data", bytes.length) + e);
        }  finally {
            close(is);
            close(bis);
        }
        return  list;
    }
}

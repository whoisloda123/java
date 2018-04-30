package com.liucan.utils.serialize;


import org.apache.log4j.Logger;

import java.io.Closeable;

/*
 * 序列化虚类
 */
public abstract class SerializeTranscoder {
    protected static Logger logger = Logger.getLogger(SerializeTranscoder.class);

    public abstract  byte[] serialize(Object obj);

    public abstract Object deserialize(byte[] bytes);

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                logger.info("Unable to close" + closeable, e);
            }
        }
    }
}

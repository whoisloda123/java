package com.liucan.utils.serialize;

import lombok.extern.slf4j.Slf4j;

import java.io.Closeable;

/*
 * 序列化虚类
 */
@Slf4j
public abstract class SerializeTranscoder {
    public abstract  byte[] serialize(Object obj);
    public abstract Object deserialize(byte[] bytes);

    public void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                log.info("[序列化]Unable to close" + closeable, e);
            }
        }
    }
}

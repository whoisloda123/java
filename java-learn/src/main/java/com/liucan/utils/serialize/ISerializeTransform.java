package com.liucan.utils.serialize;

import java.io.IOException;

/*
 * 序列化虚类
 */
public interface ISerializeTransform {
    byte[] serialize(Object obj) throws IOException;

    Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException;
}

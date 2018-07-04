package com.liucan.redis;

import com.liucan.utils.serialize.ObjectsTranscoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Ledis extends LedisBase {
    public String set(String key, String value) {
        String rtn = null;
        try {
            rtn = getJedis().setex(key, EXPIRE, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public String get(String key) {
        String rtn = null;
        try {
            rtn = getJedis().get(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    //同时获取多个key
    public List<String> mget(String... keys) {
        List<String> strings = new ArrayList<>();
        try {
            strings = getJedis().mget(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strings;
    }

    //同时设置多个key
    public String mset(String... keysvalues) {
        String rtn = null;
        try {
            rtn = getJedis().mset(keysvalues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public Long mdel(String[] keys) {
        Long rtn = null;
        try {
            rtn = getJedis().del(keys);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public Map<String, String> hgetall(String key) {
        Map<String, String> rtn = new HashMap<>();
        try {
            rtn = getJedis().hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public Long hset(String key, String filed, String value) {
        Long rtn = null;
        try {
            rtn = getJedis().hset(key, filed, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public String hget(String key, String field) {
        String rtn = null;
        try {
            rtn = getJedis().hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    //obj必须要实现java.io.Serializable接口，下面一样的
    public Long hsetObject(String key, String filed, Object obj) {
        Long rtn = null;
        try {
            rtn = getJedis().hset(key.getBytes(), filed.getBytes(), ObjectsTranscoder.getObjectsTranscoder().serialize(obj));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rtn;
    }

    public Object hgetObject(String key, String field) {
        byte[] rtn = null;
        try {
            rtn = getJedis().hget(key.getBytes(), field.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ObjectsTranscoder.getObjectsTranscoder().deserialize(rtn);
    }

    public void saddObject(String key, Object obj) {
        try {
            getJedis().sadd(key.getBytes(), ObjectsTranscoder.getObjectsTranscoder().serialize(obj));
            getJedis().expire(key.getBytes(), EXPIRE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Object> smembersAllObject(String key) {
        List<Object> list = new ArrayList<Object>();
        try {
            Set<byte[]> set = getJedis().smembers(key.getBytes());
            if (set != null && !set.isEmpty()) {
                Iterator<byte[]> it = set.iterator();
                for (; it.hasNext();) {
                    byte[] b = it.next();
                    Object obj = ObjectsTranscoder.getObjectsTranscoder().deserialize(b);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void delAllObject(String key) {
        try {
            getJedis().del(key.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        getJedis().flushAll();
    }
}

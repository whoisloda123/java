package com.liucan.redis;

import com.liucan.utils.serialize.ObjectsTranscoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Ledis extends LedisBase {
    public String set(String key, String value) {
        String rtn = null;
        try {
            rtn = jedis.setex(key, EXPIRE, value);
        } catch (Exception e) {
            LOG.error("set:", e);
        }
        return rtn;
    }

    public String get(String key) {
        String rtn = null;
        try {
            rtn = jedis.get(key);
        } catch (Exception e) {
            LOG.error("get:", e);
        }
        return rtn;
    }

    //同时获取多个key
    public List<String> mget(String... keys) {
        List<String> strings = new ArrayList<>();
        try {
            strings = jedis.mget(keys);
        } catch (Exception e) {
            LOG.error("mget:", e);
        }
        return strings;
    }

    //同时设置多个key
    public String mset(String... keysvalues) {
        String rtn = null;
        try {
            rtn = jedis.mset(keysvalues);
        } catch (Exception e) {
            LOG.error("mset:", e);
        }
        return rtn;
    }

    public Long mdel(String[] keys) {
        Long rtn = null;
        try {
            rtn = jedis.del(keys);
        } catch (Exception e) {
            LOG.error("mdel:", e);
        }
        return rtn;
    }

    public Map<String, String> hgetall(String key) {
        Map<String, String> rtn = new HashMap<>();
        try {
            rtn = jedis.hgetAll(key);
        } catch (Exception e) {
            LOG.error("hgetall:", e);
        }
        return rtn;
    }

    public Long hset(String key, String filed, String value) {
        Long rtn = null;
        try {
            rtn = jedis.hset(key, filed, value);
        } catch (Exception e) {
            LOG.error("hset:", e);
        }
        return rtn;
    }

    public String hget(String key, String field) {
        String rtn = null;
        try {
            rtn = jedis.hget(key, field);
        } catch (Exception e) {
            LOG.error("hget:", e);
        }
        return rtn;
    }

    //obj必须要实现java.io.Serializable接口，下面一样的
    public Long hsetObject(String key, String filed, Object obj) {
        Long rtn = null;
        try {
            rtn = jedis.hset(key.getBytes(), filed.getBytes(), ObjectsTranscoder.getObjectsTranscoder().serialize(obj));
        } catch (Exception e) {
            LOG.error("hsetObject:", e);
        }
        return rtn;
    }

    public Object hgetObject(String key, String field) {
        byte[] rtn = null;
        try {
            rtn = jedis.hget(key.getBytes(), field.getBytes());
        } catch (Exception e) {
            LOG.error("hgetObject:", e);
        }
        return ObjectsTranscoder.getObjectsTranscoder().deserialize(rtn);
    }

    public void saddObject(String key, Object obj) {
        try {
            jedis.sadd(key.getBytes(), ObjectsTranscoder.getObjectsTranscoder().serialize(obj));
            jedis.expire(key.getBytes(), EXPIRE);
        } catch (Exception e) {
            LOG.error("saddObject:", e);
        }
    }

    public List<Object> smembersAllObject(String key) {
        List<Object> list = new ArrayList<Object>();
        try {
            Set<byte[]> set = jedis.smembers(key.getBytes());
            if (set != null && !set.isEmpty()) {
                Iterator<byte[]> it = set.iterator();
                for (; it.hasNext();) {
                    byte[] b = it.next();
                    Object obj = ObjectsTranscoder.getObjectsTranscoder().deserialize(b);
                    list.add(obj);
                }
            }
        } catch (Exception e) {
            LOG.error("smembersAllObject:", e);
        }
        return list;
    }

    public void delAllObject(String key) {
        try {
            jedis.del(key.getBytes());
        } catch (Exception e) {
            LOG.error("delAllObject:", e);
        }
    }

    public void flush() {
        jedis.flushAll();
    }
}

package com.liucan.template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liucan
 * @version 18-11-24
 */
public class TemplateClass<K, V> {
    private Map<K, V> map = new HashMap<>();

    public V get(K k) {
        return map.get(k);
    }

    public V put(K k, V v) {
        return map.put(k, v);
    }

    public Map<K, V> getMap() {
        return map;
    }
}

package com.wait.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * @author WaitP
 */
public class PwdTable<K,V> implements Serializable {
    private String name;
    private Map<K,V> map;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<K, V> getMap() {
        return map;
    }

    public void setMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public String toString() {
        return "PwdTable{" +
                "name='" + name + '\'' +
                ", map=" + map +
                '}';
    }
}

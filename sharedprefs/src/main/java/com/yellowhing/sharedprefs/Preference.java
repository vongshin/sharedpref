package com.yellowhing.sharedprefs;

/**
 * @author huangxingzhan
 * @date 2019/11/6
 */
public interface Preference<V> {
    V getValue();
    void setValue(V v);
    void remove();
    String getKey();
}

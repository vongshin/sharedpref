package com.yellowhing.sharedprefs;

import android.content.SharedPreferences;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceLong implements Preference<Long> {
    private SharedPreferences sharedPreferences;
    private String key;
    private Long defaultValue;
    public PreferenceLong(SharedPreferences sharedPreferences, String key, Long defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Long getValue() {
        return sharedPreferences.getLong(key, defaultValue);
    }

    @Override
    public void setValue(Long v) {
        sharedPreferences.edit().putLong(key, v).apply();
    }

    @Override
    public void remove() {
        sharedPreferences.edit().remove(key).apply();
    }

    @Override
    public String getKey() {
        return key;
    }
}

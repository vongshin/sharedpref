package com.yellowhing.sharedprefs;

import android.content.SharedPreferences;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceFloat implements Preference<Float> {
    private SharedPreferences sharedPreferences;
    private String key;
    private Float defaultValue;
    public PreferenceFloat(SharedPreferences sharedPreferences, String key, Float defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Float getValue() {
        return sharedPreferences.getFloat(key, defaultValue);
    }

    @Override
    public void setValue(Float v) {
        sharedPreferences.edit().putFloat(key, v).apply();
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

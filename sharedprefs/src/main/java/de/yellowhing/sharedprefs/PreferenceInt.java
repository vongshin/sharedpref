package de.yellowhing.sharedprefs;

import android.content.SharedPreferences;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceInt implements Preference<Integer> {
    private SharedPreferences sharedPreferences;
    private String key;
    private Integer defaultValue;
    public PreferenceInt(SharedPreferences sharedPreferences, String key, Integer defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Integer getValue() {
        return sharedPreferences.getInt(key, defaultValue);
    }

    @Override
    public void setValue(Integer v) {
        sharedPreferences.edit().putInt(key, v).apply();
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

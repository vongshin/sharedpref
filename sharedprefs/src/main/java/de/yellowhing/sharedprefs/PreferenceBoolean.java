package de.yellowhing.sharedprefs;

import android.content.SharedPreferences;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceBoolean implements Preference<Boolean> {
    private SharedPreferences sharedPreferences;
    private String key;
    private Boolean defaultValue;
    public PreferenceBoolean(SharedPreferences sharedPreferences, String key, Boolean defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Boolean getValue() {
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    @Override
    public void setValue(Boolean v) {
        sharedPreferences.edit().putBoolean(key, v).apply();
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

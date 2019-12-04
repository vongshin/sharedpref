package de.yellowhing.sharedprefs;

import android.content.SharedPreferences;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceString implements Preference<String> {
    private SharedPreferences sharedPreferences;
    private String key;
    private String defaultValue;
    public PreferenceString(SharedPreferences sharedPreferences, String key, String defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public String getValue() {
        return sharedPreferences.getString(key, defaultValue);
    }

    @Override
    public void setValue(String v) {
        sharedPreferences.edit().putString(key, v).apply();
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

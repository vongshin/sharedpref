package de.yellowhing.sharedprefs;

import android.content.SharedPreferences;

import java.util.Set;

/**
 * @author huangxingzhan
 * @date 2019/11/8
 */
class PreferenceStringSet implements Preference<Set<String>> {
    private SharedPreferences sharedPreferences;
    private String key;
    private Set<String> defaultValue;
    public PreferenceStringSet(SharedPreferences sharedPreferences, String key, Set<String> defaultValue) {
        this.sharedPreferences = sharedPreferences;
        this.key = key;
        this.defaultValue = defaultValue;
    }

    @Override
    public Set<String> getValue() {
        return sharedPreferences.getStringSet(key, defaultValue);
    }

    @Override
    public void setValue(Set<String> v) {
        sharedPreferences.edit().putStringSet(key, v).apply();
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

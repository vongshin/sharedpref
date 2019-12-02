package com.yellowhing.sharedprefs.sample;

import com.yellowhing.sharedprefs.Preference;
import com.yellowhing.sharedprefs.annotation.SharedPres;
import com.yellowhing.sharedprefs.annotation.StringAttr;

/**
 * @author huangxingzhan
 * @date 2019/2/28
 */
@SharedPres("app_config")
public interface AppConfig {
    int age(int... age);

    String name(String... name);

    float money(float... money);

    long value(long... value);

    @StringAttr("123456")
    Preference<String> password();

    String info();

    void info(String info);
}

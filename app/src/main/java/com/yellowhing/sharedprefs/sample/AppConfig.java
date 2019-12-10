package com.yellowhing.sharedprefs.sample;

import de.yellowhing.sharedprefs.Preference;
import de.yellowhing.sharedprefs.annotation.SharedPres;
import de.yellowhing.sharedprefs.annotation.StringAttr;

/**
 * @author huangxingzhan
 * @date 2019/2/28
 */
@SharedPres("app_config")
public interface AppConfig {

    @StringAttr(value = "123456", key = "login_pwd")
    Preference<String> password();

    String info();

    void info(String info);
}

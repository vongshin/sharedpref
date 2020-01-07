package de.yellowhing.sharedprefs;

import android.app.AppGlobals;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import de.yellowhing.sharedprefs.annotation.Alias;
import de.yellowhing.sharedprefs.annotation.BooleanAttr;
import de.yellowhing.sharedprefs.annotation.FloatAttr;
import de.yellowhing.sharedprefs.annotation.IntAttr;
import de.yellowhing.sharedprefs.annotation.LongAttr;
import de.yellowhing.sharedprefs.annotation.SharedPrefs;
import de.yellowhing.sharedprefs.annotation.StringSetAttr;
import de.yellowhing.sharedprefs.annotation.StringAttr;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author huangxingzhan
 * @date 2019/2/28
 */
public class Refine {
    public final static int INT_DEF_VALUE = 0;
    public final static boolean BOOLEAN_DEF_VALUE = false;
    public final static long LONG_DEF_VALUE = 0L;
    public final static float FLOAT_DEF_VALUE = 0.0f;
    public final static String STRING_DEF_VALUE = "";
    public final static String[] STRINGS_DEF_VALUE = {};

    public static <T> T create(Class<T> tClass) {
        return create(tClass, AppGlobals.getInitialApplication());
    }

    public static <T> T create(Class<T> tClass, Context context) {
        final SharedPrefs sharedPres = tClass.getAnnotation(SharedPrefs.class);
        String name = null;
        int mode = Context.MODE_PRIVATE;
        if (sharedPres != null) {
            name = sharedPres.value();
            mode = sharedPres.mode();
        }
        Object object = Proxy.newProxyInstance(tClass.getClassLoader(), new Class[]{tClass}, new AbstractInvocationHandler(name, mode, context) {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return parse(method, args);
            }
        });
        return (T)object;
    }

    static abstract class AbstractInvocationHandler implements InvocationHandler {
        SharedPreferences sharedPreferences = null;
        static Map<Method, Preference> sPreferenceCache = new HashMap<>();
        AbstractInvocationHandler(String name, int mode, Context context) {
            if(TextUtils.isEmpty(name)){
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
            }else{
                sharedPreferences = context.getSharedPreferences(name, mode);
            }

        }
        private Object parseInt(Method method, Object[] args){
            IntAttr intAttr = method.getAnnotation(IntAttr.class);
            int defValue = intAttr == null ? INT_DEF_VALUE : intAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = intAttr == null || TextUtils.isEmpty(intAttr.key()) ? key : intAttr.key();
            if(args == null || args.length == 0){
                return getInt(key, defValue);
            }else{
                Object p = args[0];
                int value;
                if(p.getClass() == int[].class || p.getClass() == Integer[].class){
                    int[] values = (int[]) p;
                    if(values.length == 0){
                        return getInt(key, defValue);
                    }
                    value = values[0];
                }else if(p.getClass() == int.class || p.getClass() == Integer.class){
                    value = (int )p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putInt(key, value);
                return value;
            }
        }

        private Object parseLong(Method method, Object[] args){
            LongAttr longAttr = method.getAnnotation(LongAttr.class);
            long defValue = longAttr == null ? LONG_DEF_VALUE : longAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = longAttr == null || TextUtils.isEmpty(longAttr.key()) ? key : longAttr.key();
            if(args == null || args.length == 0){
                return getLong(key, defValue);
            }else{
                Object p = args[0];
                long value;
                if(p.getClass() == long[].class || p.getClass() == Long[].class){
                    long[] values = (long[]) p;
                    if(values.length == 0){
                        return getLong(key, defValue);
                    }
                    value = values[0];
                }else if(p.getClass() == long.class || p.getClass() == Long.class){
                    value = (long )p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putLong(key, value);
                return value;
            }
        }

        private Object parseBoolean(Method method, Object[] args){
            BooleanAttr booleanAttr = method.getAnnotation(BooleanAttr.class);
            boolean defValue = booleanAttr == null ? BOOLEAN_DEF_VALUE : booleanAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = booleanAttr == null || TextUtils.isEmpty(booleanAttr.key()) ? key : booleanAttr.key();
            if(args == null || args.length == 0){
                return getBoolean(key, defValue);
            }else{
                Object p = args[0];
                boolean value;
                if(p.getClass() == boolean[].class || p.getClass() == Boolean[].class){
                    boolean[] values = (boolean[]) p;
                    if(values.length == 0){
                        return getBoolean(key, defValue);
                    }
                    value = values[0];
                }else if(p.getClass() == boolean.class || p.getClass() == Boolean.class){
                    value = (boolean)p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putBoolean(key, value);
                return value;
            }
        }

        private Object parseFloat(Method method, Object[] args){
            FloatAttr floatAttr = method.getAnnotation(FloatAttr.class);
            float defValue = floatAttr == null ? FLOAT_DEF_VALUE : floatAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = floatAttr == null || TextUtils.isEmpty(floatAttr.key()) ? key : floatAttr.key();
            if(args == null || args.length == 0){
                return getFloat(key, defValue);
            }else{
                Object p = args[0];
                float value;
                if(p.getClass() == float[].class || p.getClass() == Float[].class){
                    float[] values = (float[]) p;
                    if(values.length == 0){
                        return getFloat(key, defValue);
                    }
                    value = values[0];
                }else if(p.getClass() == float.class || p.getClass() == Float.class){
                    value = (float )p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putFloat(key, value);
                return value;
            }
        }

        private Object parseString(Method method, Object[] args){
            StringAttr stringAttr = method.getAnnotation(StringAttr.class);
            String defValue = stringAttr == null ? STRING_DEF_VALUE : stringAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = stringAttr == null || TextUtils.isEmpty(stringAttr.key()) ? key : stringAttr.key();
            if(args == null || args.length == 0){
                return getString(key, defValue);
            }else{
                Object p = args[0];
                String value;
                if(p.getClass() == String[].class){
                    String[] values = (String[]) p;
                    if(values.length == 0){
                        return getString(key, defValue);
                    }
                    value = values[0];
                }else if(p.getClass() == String.class){
                    value = (String )p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putString(key, value);
                return value;
            }
        }

        private Object parseStringSet(Method method, Object[] args){
            StringSetAttr stringSetAttr = method.getAnnotation(StringSetAttr.class);
            String[] defValue = stringSetAttr == null ? STRINGS_DEF_VALUE : stringSetAttr.value();
            Alias alias = method.getAnnotation(Alias.class);
            String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
            key = stringSetAttr == null || TextUtils.isEmpty(stringSetAttr.key()) ? key : stringSetAttr.key();
            if(args == null || args.length == 0){
                return getStringSet(key, new HashSet<>(Arrays.asList(defValue)));
            }else{
                Object p = args[0];
                Set<String> value;
                if(p.getClass() == Set[].class){
                    Set<String>[] values = (Set<String>[]) p;
                    if(values.length == 0){
                        return getStringSet(key, new HashSet<>(Arrays.asList(defValue)));
                    }
                    value = values[0];
                }else if(p.getClass() == String[].class){
                    value = (Set<String>)p;
                }else{
                    throw new UnsupportedOperationException();
                }
                putStringSet(key, value);
                return value;
            }
        }
        Object parse(Method method, Object[] args) {
            Class<?> tClass = method.getReturnType();
            if(tClass == void.class || tClass == Void.class){
                Class<?>[] classes = method.getParameterTypes();
                if(classes.length > 0){
                    tClass = classes[0];
                }else{
                    throw new UnsupportedOperationException();
                }
            }
            if (tClass == int.class || tClass == Integer.class) {
                return parseInt(method, args);
            } else if (tClass == boolean.class || tClass == Boolean.class) {
                return parseBoolean(method, args);
            } else if (tClass == long.class || tClass == Long.class) {
                return parseLong(method, args);
            } else if (tClass == float.class || tClass == Float.class) {
                return parseFloat(method, args);
            } else if (tClass == String.class) {
                return parseString(method, args);
            } else if (tClass == String[].class) {
                return parseStringSet(method, args);
            } else if (tClass == Preference.class) {
                Preference preference = sPreferenceCache.get(method);
                if(preference != null) return preference;
                Type type = method.getGenericReturnType();
                ParameterizedType genType = (ParameterizedType)type;
                Type[] types = genType.getActualTypeArguments();
                final Type ct= types[0];
                Alias alias = method.getAnnotation(Alias.class);
                String key = alias == null || TextUtils.isEmpty(alias.value()) ? method.getName() : alias.value();
                if (Boolean.class.equals(ct)) {
                    BooleanAttr booleanAttr = method.getAnnotation(BooleanAttr.class);
                    boolean defValue = booleanAttr == null ? BOOLEAN_DEF_VALUE : booleanAttr.value();
                    key = booleanAttr == null || TextUtils.isEmpty(booleanAttr.key()) ? key : booleanAttr.key();
                    preference = new PreferenceBoolean(sharedPreferences, key, defValue);
                } else if (Integer.class.equals(ct)) {
                    IntAttr intAttr = method.getAnnotation(IntAttr.class);
                    int defValue = intAttr == null ? INT_DEF_VALUE : intAttr.value();
                    key = intAttr == null || TextUtils.isEmpty(intAttr.key()) ? key : intAttr.key();
                    preference = new PreferenceInt(sharedPreferences, key, defValue);
                } else if (Long.class.equals(ct)) {
                    LongAttr longAttr = method.getAnnotation(LongAttr.class);
                    long defValue = longAttr == null ? LONG_DEF_VALUE : longAttr.value();
                    key = longAttr == null || TextUtils.isEmpty(longAttr.key()) ? key : longAttr.key();
                    preference = new PreferenceLong(sharedPreferences, key, defValue);
                } else if (Float.class.equals(ct)) {
                    FloatAttr floatAttr = method.getAnnotation(FloatAttr.class);
                    float defValue = floatAttr == null ? FLOAT_DEF_VALUE : floatAttr.value();
                    key = floatAttr == null || TextUtils.isEmpty(floatAttr.key()) ? key : floatAttr.key();
                    preference = new PreferenceFloat(sharedPreferences, key, defValue);
                } else if (String.class.equals(ct)) {
                    StringAttr stringAttr = method.getAnnotation(StringAttr.class);
                    final String defValue = stringAttr == null ? STRING_DEF_VALUE : stringAttr.value();
                    key = stringAttr == null || TextUtils.isEmpty(stringAttr.key()) ? key : stringAttr.key();
                    preference = new PreferenceString(sharedPreferences, key, defValue);
                }else if (Set.class.equals(ct)) {
                    StringSetAttr stringSetAttr = method.getAnnotation(StringSetAttr.class);
                    final String[] defValue = stringSetAttr == null ? STRINGS_DEF_VALUE : stringSetAttr.value();
                    key = stringSetAttr == null || TextUtils.isEmpty(stringSetAttr.key()) ? key : stringSetAttr.key();
                    preference = new PreferenceStringSet(sharedPreferences, key, new HashSet<>(Arrays.asList(defValue)));
                }else{
                    throw new UnsupportedOperationException();
                }
                sPreferenceCache.put(method, preference);
                return preference;
            }
            else {
                throw new UnsupportedOperationException();
            }
        }


        private boolean getBoolean(String key, boolean defValue) {
            return sharedPreferences.getBoolean(key, defValue);
        }

        private int getInt(String key, int defValue) {
            return sharedPreferences.getInt(key, defValue);
        }

        private long getLong(String key, long defValue) {
            return sharedPreferences.getLong(key, defValue);
        }

        private float getFloat(String key, float defValue) {
            return sharedPreferences.getFloat(key, defValue);
        }

        private Set<String> getStringSet(String key, @Nullable Set<String> defValues) {
            return sharedPreferences.getStringSet(key, defValues);
        }

        private String getString(String key, @Nullable String defValue) {
            return sharedPreferences.getString(key, defValue);
        }

        private void putBoolean(String key, boolean value) {
            sharedPreferences.edit().putBoolean(key, value).apply();
        }

        private void putInt(String key, int value) {
            sharedPreferences.edit().putInt(key, value).apply();
        }

        private void putLong(String key, long value) {
            sharedPreferences.edit().putLong(key, value).apply();
        }

        private void putFloat(String key, float value) {
            sharedPreferences.edit().putFloat(key, value).apply();
        }

        private void putString(String key, @Nullable String value) {
            sharedPreferences.edit().putString(key, value).apply();
        }

        private void putStringSet(String key, @Nullable Set<String> value) {
            sharedPreferences.edit().putStringSet(key, value).apply();
        }
    }
}

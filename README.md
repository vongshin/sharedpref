# sharedpref
## 简化使用sharedpreferences

```java
 implementation 'de.yellowhing.sharedprefs:sharedprefs:0.0.3'
```

### step 1 定义接口配置

```java
@SharedPres(value = "app_config", mode = Context.MODE_PRIVATE)
public interface AppConfig {
    @StringAttr(value = "jake", key = "name")
    Preference<String> name();

    @StringAttr(value = "jake")
    String name(String... value);
    
    @StringAttr(value = "jake", key = "name")
    String getName();
    
    @StringAttr(key = "name")
    void setName(String name);
}
```
- 接口定义说明：<code>@SharedPres</code>用于配置命名(name)和模式(mode)
- <code>@BooleanAttr @IntAttr @LongAttr @FloatAttr @StringAttr @StringSetAttr</code> 用于配置key defValue
- 方法定义
   - <code>T name(T... value);</code> 入参是可变参数，调用时，不填表示获取，填则表示保存
   - <code>Preference<T> name();</code> 获取Preference对象进行操作


### step 2 生成接口实例，调用
```java
AppConfig config = Refine.create(AppConfig.class);
// get name by Preference
config.name().getValue();
//get name
config.getName();
//set name == jake
config.name("jake");
```

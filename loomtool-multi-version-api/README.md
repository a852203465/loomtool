# API多版本管理模块

## 1.引入依赖
```xml
<dependency>
    <groupId>cn.darkjrong</groupId>
    <artifactId>loomtool-multi-version-api</artifactId>
</dependency>
```

### 2.yml配置
    必须配置enabled: true，否则默认false不起作用
```yaml
loom:
  multi:
    version:
      enabled: true
      # 默认：X-API-VERSION
      header-name: x-api-version
      # 默认：X-API-VERSION
      parameter-name: x-api-version
```

### 3.使用方式
#### 3.1注解使用
```java
@Slf4j
@RestController
@RequestMapping("")
public class VersionController {

    @GetMapping(value = "get")
    public Map<String, Object> getD() {
        log.info("getD {}", DateUtil.now());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", "默认版本");
        return map;
    }

    @GetMapping(value = "get")
    @ApiVersion("v1") // 设定API版本号
    public Map<String, Object> getV1() {
        log.info("getV1 {}", DateUtil.now());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", "V1");
        return map;
    }

    @GetMapping(value = "get")
    @ApiVersion("v2")  // 设定API版本号
    public Map<String, Object> getV2() {
        log.info("getV2 {}", DateUtil.now());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "成功");
        map.put("data", "V2");
        return map;
    }

}
```

#### 3.2API请求方式
```text

http://localhost:8080/get?x-api-version=v2
http://localhost:8080/get?x-api-version=v1
http://localhost:8080/get

```







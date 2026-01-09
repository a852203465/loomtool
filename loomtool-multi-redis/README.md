# Redis多数据源模块

## 引入依赖
```xml
<dependencies>
    <dependency>
        <groupId>cn.darkjrong</groupId>
        <artifactId>loomtool-multi-redis</artifactId>
    </dependency>
</dependencies>

```

## 使用说明
### 在application.yml中加入如下配置
```yaml
# 默认的
spring:
  cache:
    redis:
      time-to-live: 60s
    type: redis
  redis:
    host: dev.com
    port: 6379
    database: 6
    timeout: 10000ms
    lettuce:
      pool:
        max-active: 500
        max-idle: 100
        min-idle: 5
        max-wait: 10000ms
xdc:
  saas:
    multi-redis:
      # 必须为true
      enable-multi: true
      multi:
        redis1:
          host: dev.com
          port: 6379
          database: 7
          timeout: 10000ms
          lettuce:
            pool:
              max-active: 500
              max-idle: 100
              min-idle: 5
              max-wait: 10000ms
        redis2:
          host: dev.com
          port: 6379
          database: 8
          timeout: 10000ms
          lettuce:
            pool:
              max-active: 500
              max-idle: 100
              min-idle: 5
              max-wait: 10000ms

```
### 配置来源动态增加
#### 实现‘AbstractRoutingRedisDataSource’类
```java
public class DynamicRedisDataSource extends AbstractRoutingRedisDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return DynamicRedisContext.getDataSourceKey();
    }

    @Override
    protected Integer determineCurrentLookupDb() {
        return DynamicRedisContext.getDataSourceDb();
    }

    public void setDataSources(Map<String, RedisConnectionFactory> dataSources) {
        super.setTargetDataSources(dataSources);
    }

}
```
#### 初始化其他来源方式的多数据源

```java
import java.util.Collections;

/**
 * Db redis属性适配器
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
@Component
public class DbRedisPropertiesAdapter implements RedisPropertiesAdapter {

    @Override
    public Map<String, RedisProperties> redisProperties() {
        
        // 返回数据源,可借助 DynamicRedisUtils
        return Collections.emptyMap();
    }
}
```

















































package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Redis多数据源属性
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.redis.multi-redis")
public class MultiRedisProperties {

    /**
     * 是否开启多数据源, 默认:false
     */
    private Boolean enabled = false;

    /**
     * 数据源配置
     */
    private Map<String, RedisProperties> multi;


}

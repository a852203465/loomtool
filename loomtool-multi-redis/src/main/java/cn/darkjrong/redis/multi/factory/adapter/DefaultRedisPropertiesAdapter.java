package cn.darkjrong.redis.multi.factory.adapter;

import cn.darkjrong.spring.boot.autoconfigure.MultiRedisProperties;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.Map;

/**
 * 默认redis属性适配器
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
public class DefaultRedisPropertiesAdapter implements RedisPropertiesAdapter {

    private final MultiRedisProperties multiRedisProperties;

    public DefaultRedisPropertiesAdapter(MultiRedisProperties multiRedisProperties) {
        this.multiRedisProperties = multiRedisProperties;
    }

    @Override
    public Map<String, RedisProperties> redisProperties() {
        return multiRedisProperties.getMulti();
    }
}

package cn.darkjrong.redis.multi.factory.adapter;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.util.Map;

/**
 * redis属性适配器
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
public interface RedisPropertiesAdapter {

    /**
     * redis属性
     *
     * @return {@link Map }<{@link String }, {@link RedisProperties }> key:数据源标识,value:属性
     */
    Map<String, RedisProperties> redisProperties();














}

package cn.darkjrong.redis.multi.configuration;

import cn.darkjrong.redis.multi.MultiRedisConnectionFactory;
import cn.darkjrong.redis.multi.enums.MultiRedisEnum;
import cn.darkjrong.redis.multi.factory.adapter.DefaultRedisPropertiesAdapter;
import cn.darkjrong.redis.multi.factory.adapter.RedisPropertiesAdapter;
import cn.darkjrong.redis.multi.factory.connector.RedisConnectFactory;
import cn.darkjrong.spring.boot.autoconfigure.MultiRedisProperties;
import cn.hutool.core.collection.CollectionUtil;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.RedisConnectionFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 多redis配置
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
@Configuration
public class MultiRedisConfig {

    @Bean
    @ConditionalOnMissingBean(RedisConnectionFactory.class)
    public MultiRedisConnectionFactory multiRedisConnectionFactory(Environment env,
                                                                   RedisConnectFactory factory,
                                                                   RedisPropertiesAdapter redisPropertiesAdapter) {
        Map<String, RedisConnectionFactory> connectionFactoryMap = new HashMap<>();

        RedisProperties defaultRedisProperties = Binder.get(env)
                .bindOrCreate(MultiRedisEnum.REDIS_PREFIX.getValue(), RedisProperties.class);
        RedisConnectionFactory connectionFactory = factory.connectionFactory(defaultRedisProperties);
        connectionFactoryMap.put(MultiRedisEnum.MASTER.getValue(), connectionFactory);

        Map<String, RedisProperties> multiMap = redisPropertiesAdapter.redisProperties();
        if (CollectionUtil.isEmpty(multiMap)) {
            throw new BeanCreationException("multi redis configuration attribute cannot be empty");
        }
        multiMap.forEach((k, v) -> connectionFactoryMap.put(k, factory.connectionFactory(v)));

        MultiRedisConnectionFactory multiRedisConnectionFactory = new MultiRedisConnectionFactory();
        multiRedisConnectionFactory.setTargetDataSources(connectionFactoryMap);
        multiRedisConnectionFactory.setDefaultTargetDataSource(connectionFactory);
        return multiRedisConnectionFactory;
    }

    @Bean
    @ConditionalOnMissingBean(RedisPropertiesAdapter.class)
    public RedisPropertiesAdapter redisPropertiesAdapter(MultiRedisProperties multiRedisProperties) {
        return new DefaultRedisPropertiesAdapter(multiRedisProperties);
    }









}

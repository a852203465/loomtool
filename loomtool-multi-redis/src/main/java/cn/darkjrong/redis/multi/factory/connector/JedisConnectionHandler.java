package cn.darkjrong.redis.multi.factory.connector;

import cn.darkjrong.redis.multi.factory.connection.JedisConnectionConfigure;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.data.redis.JedisClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.stereotype.Component;

/**
 * Jedis连接工厂处理器
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
@Slf4j
@Component
@AllArgsConstructor
public class JedisConnectionHandler implements RedisConnectionHandler {

    private final ObjectProvider<RedisSentinelConfiguration> sentinelConfigurationProvider;
    private final ObjectProvider<RedisClusterConfiguration> clusterConfigurationProvider;
    private final ObjectProvider<JedisClientConfigurationBuilderCustomizer> builderCustomizers;

    @Override
    public Boolean supports(RedisProperties.ClientType clientType) {
        return RedisProperties.ClientType.JEDIS.equals(clientType);
    }

    @Override
    public RedisConnectionFactory connect(RedisProperties redisProperties) {
        JedisConnectionConfigure connectionConfigure =
                new JedisConnectionConfigure(redisProperties, sentinelConfigurationProvider, clusterConfigurationProvider);
        return connectionConfigure.redisConnectionFactory(builderCustomizers);
    }
}

package cn.darkjrong.redis.multi.factory.connector;

import cn.darkjrong.redis.multi.factory.connection.LettuceConnectionConfigure;
import io.lettuce.core.resource.ClientResources;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.stereotype.Component;

/**
 * 默认连接工厂处理器
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
@Slf4j
@Component
@AllArgsConstructor
public class EmptyConnectionHandler implements RedisConnectionHandler {

    private final ObjectProvider<RedisSentinelConfiguration> sentinelConfigurationProvider;
    private final ObjectProvider<RedisClusterConfiguration> clusterConfigurationProvider;
    private final ObjectProvider<LettuceClientConfigurationBuilderCustomizer> builderCustomizers;
    private final ClientResources clientResources;

    @Override
    public Boolean supports(RedisProperties.ClientType clientType) {
        return Boolean.TRUE;
    }

    @Override
    public RedisConnectionFactory connect(RedisProperties redisProperties) {
        LettuceConnectionConfigure connectionConfigure =
                new LettuceConnectionConfigure(redisProperties, sentinelConfigurationProvider, clusterConfigurationProvider);
        return connectionConfigure.redisConnectionFactory(builderCustomizers, clientResources);
    }
}

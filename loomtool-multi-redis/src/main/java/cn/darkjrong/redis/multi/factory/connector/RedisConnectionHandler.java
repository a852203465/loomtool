package cn.darkjrong.redis.multi.factory.connector;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * Redis连接工厂处理器
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
public interface RedisConnectionHandler {

    /**
     * 支持类型
     *
     * @param clientType 客户类型
     * @return {@link Boolean }
     */
    Boolean supports(RedisProperties.ClientType clientType);

    /**
     * 连接
     *
     * @param redisProperties redis属性
     * @return {@link RedisConnectionFactory }
     */
    RedisConnectionFactory connect(RedisProperties redisProperties);



}

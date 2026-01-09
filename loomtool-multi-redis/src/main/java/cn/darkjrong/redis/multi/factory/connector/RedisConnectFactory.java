package cn.darkjrong.redis.multi.factory.connector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Redis连接工厂
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
@Slf4j
@Component
public class RedisConnectFactory {
    
    @Autowired
    private List<RedisConnectionHandler> handlers;

    @Autowired
    private EmptyConnectionHandler emptyHandler;

    public RedisConnectionFactory connectionFactory(RedisProperties redisProperties) {
        RedisConnectionHandler handler = handlers.stream()
                .filter(a -> a.supports(redisProperties.getClientType()))
                .findAny().orElse(emptyHandler);
        return handler.connect(redisProperties);
    }
    
    
    
    
    
    
    
    
    
    

}

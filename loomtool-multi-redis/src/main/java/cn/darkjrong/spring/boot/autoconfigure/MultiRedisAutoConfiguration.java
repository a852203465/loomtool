package cn.darkjrong.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Redis多数据源自动配置
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
@Configuration
@ComponentScan("cn.darkjrong.redis.multi")
@ConditionalOnProperty(prefix = "spring.redis.multi-redis", value = "enabled", matchIfMissing = false)
@EnableConfigurationProperties(MultiRedisProperties.class)
public class MultiRedisAutoConfiguration {



}

package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 线程池属性
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.task.pool")
public class LoomExecutorPoolProperties {

    /**
     *  是否开启，默认：false
     */
    private boolean enabled = false;

    /**
     * 核心数
     */
    private Integer corePoolSize;

    /**
     * 最大数
     */
    private Integer maxPoolSize;

    /**
     * 活跃时间
     */
    private Integer keepAliveSeconds;

    /**
     * 队列大小
     */
    private Integer queueCapacity;

    /**
     * 线程名前缀
     */
    private String threadName = "asyncExecutor-";










}

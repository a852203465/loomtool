package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 执行器属性
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.task.executor")
public class LoomExecutorProperties {

    /**
     *  是否开启，默认：false
     */
    private boolean enabled = false;

    /**
     * 核心数
     */
    private Integer corePoolSize = 5;

    /**
     * 最大数
     */
    private Integer maxPoolSize = 50;

    /**
     * 活跃时间，单位：秒
     */
    private Integer keepAliveSeconds = 5;

    /**
     * 队列大小
     */
    private Integer queueCapacity = 500;










}

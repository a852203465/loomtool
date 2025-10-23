package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 定时器属性
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.scheduled")
public class LoomScheduledProperties {

    /**
     * 是否开启，默认：false
     */
    private boolean enabled = false;

    /**
     * 线程池个数，默认：10
     */
    private Integer poolSize = 10;









}

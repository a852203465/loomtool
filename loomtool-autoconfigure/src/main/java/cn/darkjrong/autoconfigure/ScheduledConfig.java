package cn.darkjrong.autoconfigure;

import cn.darkjrong.spring.boot.autoconfigure.LoomScheduledProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 *  定时器连接池
 * @date 2019/6/11 20:45:22
 * @author Rong.Jia
 */
@Slf4j
@Data
@Configuration
@ConditionalOnProperty(prefix = "loom.scheduled", name = "enabled", havingValue = "true")
public class ScheduledConfig {

    private final LoomScheduledProperties loomScheduledProperties;

    public ScheduledConfig(LoomScheduledProperties loomScheduledProperties) {
        this.loomScheduledProperties = loomScheduledProperties;
    }

    @Bean
    public TaskScheduler taskScheduler(){
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(loomScheduledProperties.getPoolSize());
        taskScheduler.initialize();
        return taskScheduler;
    }

}

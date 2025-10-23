package cn.darkjrong.autoconfigure;

import cn.darkjrong.spring.boot.autoconfigure.LoomExecutorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 任务执行器配置
 * @author Rong.Jia
 * @date 2023/07/01
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "loom.task.executor", name = "enabled", havingValue = "true")
public class TaskExecutorConfig {

    private final LoomExecutorProperties loomExecutorProperties;

    public TaskExecutorConfig(LoomExecutorProperties loomExecutorProperties) {
        this.loomExecutorProperties = loomExecutorProperties;
    }

    @Primary
    @Bean
    public TaskExecutor taskExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程池大小
        executor.setCorePoolSize(loomExecutorProperties.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(loomExecutorProperties.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(loomExecutorProperties.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(loomExecutorProperties.getKeepAliveSeconds());

        //线程名字前缀
        executor.setThreadNamePrefix("taskExecutor-");

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }


}

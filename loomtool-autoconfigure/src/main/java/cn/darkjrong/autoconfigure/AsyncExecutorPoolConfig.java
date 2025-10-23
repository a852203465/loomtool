package cn.darkjrong.autoconfigure;

import cn.darkjrong.spring.boot.autoconfigure.LoomExecutorPoolProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 线程池配置
 *
 * @author Rong.Jia
 * @date 2021/04/29 08:58
 */
@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "loom.task.pool", name = "enabled", havingValue = "true")
public class AsyncExecutorPoolConfig implements AsyncConfigurer {

    private final LoomExecutorPoolProperties loomExecutorPoolProperties;

    public AsyncExecutorPoolConfig(LoomExecutorPoolProperties loomExecutorPoolProperties) {
        this.loomExecutorPoolProperties = loomExecutorPoolProperties;
    }

    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程池大小
        executor.setCorePoolSize(loomExecutorPoolProperties.getCorePoolSize());
        //最大线程数
        executor.setMaxPoolSize(loomExecutorPoolProperties.getMaxPoolSize());
        //队列容量
        executor.setQueueCapacity(loomExecutorPoolProperties.getQueueCapacity());
        //活跃时间
        executor.setKeepAliveSeconds(loomExecutorPoolProperties.getKeepAliveSeconds());

        //线程名字前缀
        executor.setThreadNamePrefix(loomExecutorPoolProperties.getThreadName());

        // setRejectedExecutionHandler：当pool已经达到max size的时候，如何处理新任务
        // CallerRunsPolicy：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> log.error("线程池执行任务发生未知异常", ex);
    }
}

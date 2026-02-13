package cn.darkjrong.redis.multi;

import cn.hutool.core.util.ObjectUtil;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisClusterConnection;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConnection;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;

/**
 * 抽象路由 Redis 数据源
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
public abstract class AbstractRoutingRedisDataSource implements InitializingBean, DisposableBean, RedisConnectionFactory /*ReactiveRedisConnectionFactory*/ {

    private Map<String, RedisConnectionFactory> targetDataSources;
    private RedisConnectionFactory defaultTargetDataSource;

    public void setDefaultTargetDataSource(RedisConnectionFactory defaultTargetDataSource) {
        this.defaultTargetDataSource = defaultTargetDataSource;
    }

    public void setTargetDataSources(Map<String, RedisConnectionFactory> targetDataSources) {
        this.targetDataSources = targetDataSources;
    }

    public Map<String, RedisConnectionFactory> getTargetDataSources() {
        return targetDataSources;
    }

    public RedisConnectionFactory getDefaultTargetDataSource() {
        return defaultTargetDataSource;
    }

    @Override
    public void destroy() throws Exception {
        for (Map.Entry<String, RedisConnectionFactory> entry : targetDataSources.entrySet()) {
            RedisConnectionFactory value = entry.getValue();
            Class<?> clazz = value.getClass();
            Method method = clazz.getMethod("destroy");
            if (ObjectUtil.isNotNull(method)) {
                method.invoke(value);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        for (Map.Entry<String, RedisConnectionFactory> entry : targetDataSources.entrySet()) {
            RedisConnectionFactory value = entry.getValue();
            Class<?> clazz = value.getClass();
            Method method = clazz.getMethod("afterPropertiesSet");
            if (ObjectUtil.isNotNull(method)) {
                method.invoke(value);
            }
        }
    }

//    @Override
//    public ReactiveRedisConnection getReactiveConnection() {
//        return currentLettuceConnectionFactory().getReactiveConnection();
//    }

//    @Override
//    public ReactiveRedisClusterConnection getReactiveClusterConnection() {
//        return currentLettuceConnectionFactory().getReactiveClusterConnection();
//    }

    protected abstract String determineCurrentLookupKey();

    protected RedisConnectionFactory determineTargetDataSource() {
        Assert.notNull(this.targetDataSources, "DataSource router not initialized");
        String lookupKey = determineCurrentLookupKey();
        RedisConnectionFactory dataSource = this.targetDataSources.get(lookupKey);
        if (dataSource == null && lookupKey == null) {
            dataSource = this.defaultTargetDataSource;
        }
        if (dataSource == null) {
            throw new IllegalStateException("Cannot determine target DataSource for lookup key [" + lookupKey + "]");
        }
        return dataSource;
    }

    @Override
    public RedisConnection getConnection() {
        return determineTargetDataSource().getConnection();
    }

    @Override
    public RedisClusterConnection getClusterConnection() {
        return determineTargetDataSource().getClusterConnection();
    }

    @Override
    public boolean getConvertPipelineAndTxResults() {
        return determineTargetDataSource().getConvertPipelineAndTxResults();
    }

    @Override
    public RedisSentinelConnection getSentinelConnection() {
        return determineTargetDataSource().getSentinelConnection();
    }

    @Override
    public DataAccessException translateExceptionIfPossible(RuntimeException ex) {
        return determineTargetDataSource().translateExceptionIfPossible(ex);
    }

}
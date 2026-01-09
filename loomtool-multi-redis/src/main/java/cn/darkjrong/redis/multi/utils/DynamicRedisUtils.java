package cn.darkjrong.redis.multi.utils;

import cn.darkjrong.redis.multi.domain.CacheInfo;
import cn.darkjrong.redis.multi.enums.DeploymentMode;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 动态Redis工具类
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
@Slf4j
public class DynamicRedisUtils {

    /**
     * 创建redis属性
     *
     * @param effectiveCacheInfos 有效缓存信息
     * @return {@link Map }<{@link String }, {@link RedisProperties }>
     */
    public static Map<String, RedisProperties> createRedisProperties(List<CacheInfo> effectiveCacheInfos) {
        if (CollUtil.isNotEmpty(effectiveCacheInfos)) {
            Map<String, RedisProperties> redisPropertiesList = new HashMap<>();
            for (CacheInfo effectiveCacheInfo : effectiveCacheInfos) {
                RedisProperties redisProperties = new RedisProperties();
                redisProperties.setDatabase(Convert.toInt(effectiveCacheInfo.getDatabaseName()));

                DeploymentMode deploymentMode = DeploymentMode.valueOf(effectiveCacheInfo.getDeploymentMode());
                if (DeploymentMode.standalone.equals(deploymentMode)) {
                    List<String> serverAddresses = StrUtil.split(effectiveCacheInfo.getServerAddresses(), ":");
                    redisProperties.setPort(Convert.toInt(serverAddresses.get(1)));
                    redisProperties.setHost(serverAddresses.get(0));
                }else if (DeploymentMode.sentinel.equals(deploymentMode)) {
                    RedisProperties.Sentinel sentinel = new RedisProperties.Sentinel();
                    sentinel.setMaster(effectiveCacheInfo.getClusterName());
                    sentinel.setNodes(StrUtil.split(effectiveCacheInfo.getServerAddresses(), StrUtil.COMMA));
                    sentinel.setPassword(effectiveCacheInfo.getPassword());
                    redisProperties.setSentinel(sentinel);
                }else if (DeploymentMode.cluster.equals(deploymentMode)) {
                    RedisProperties.Cluster cluster = new RedisProperties.Cluster();
                    cluster.setNodes(StrUtil.split(effectiveCacheInfo.getServerAddresses(), StrUtil.COMMA));
                    redisProperties.setCluster(cluster);
                }
                redisProperties.setUsername(effectiveCacheInfo.getUsername());
                redisProperties.setPassword(effectiveCacheInfo.getPassword());

                redisProperties.setTimeout(Duration.ofMillis(effectiveCacheInfo.getReadTimeout()));
                redisProperties.setConnectTimeout(Duration.ofMillis(effectiveCacheInfo.getConnectTimeout()));
                redisProperties.setClientName(effectiveCacheInfo.getCode());
                redisProperties.setClientType(RedisProperties.ClientType.LETTUCE);

                RedisProperties.Pool pool = new RedisProperties.Pool();
                pool.setMaxIdle(effectiveCacheInfo.getMaxIdle());
                pool.setMinIdle(effectiveCacheInfo.getMinIdle());
                pool.setMaxActive(effectiveCacheInfo.getMaxActive());
                redisProperties.getLettuce().setPool(pool);
                redisPropertiesList.put(effectiveCacheInfo.getCode(), redisProperties);
                return redisPropertiesList;
            }
        }
        return Collections.emptyMap();
    }


}

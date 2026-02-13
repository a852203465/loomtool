package cn.darkjrong.redis.multi;

import cn.darkjrong.redis.multi.supports.MultiRedisContext;

/**
 * Multi Redis 连接工厂
 *
 * @author Rong.Jia
 * @date 2024/09/02
 */
public class MultiRedisConnectionFactory extends AbstractRoutingRedisDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return MultiRedisContext.getDataSourceKey();
    }


}

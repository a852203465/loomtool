package cn.darkjrong.redis.multi.supports;

import cn.hutool.core.util.StrUtil;
import cn.darkjrong.redis.multi.enums.MultiRedisEnum;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * 动态Redis上下文
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
public class MultiRedisContext {

    private static final ThreadLocal<String> currentRedisNameHolder = new ThreadLocal<String>() {
        @Override
        protected String initialValue() {
            return MultiRedisEnum.MASTER.getValue();
        }
    };

    /**
     * 数据源的 key集合，用于切换时判断数据源是否存在
     */
    private static final Set<String> dataSourceKeys = new HashSet<>();

    public static void setDataSourceKey(String currentRedisName) {
        if (StrUtil.isNotBlank(currentRedisName)) {
            currentRedisNameHolder.set(currentRedisName);
        }
    }

    public static void setDataSourceKey(String currentRedisName, Integer db) {
        currentRedisNameHolder.set(currentRedisName);
    }

    public static String getDataSourceKey() {
        return currentRedisNameHolder.get();
    }

    public static void clearDataSource() {
        currentRedisNameHolder.remove();
    }

    /**
     * 判断是否包含数据源
     *
     * @param key 数据源
     * @return {@link Boolean }
     */
    public static Boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

    /**
     * 添加数据源Keys
     *
     * @param keys keys
     * @return {@link Boolean }
     */
    public static Boolean addDataSourceKeys(Collection<String> keys) {
        return dataSourceKeys.addAll(keys);
    }

    /**
     * 删除数据源
     * @param key key
     */
    public static void deleteDataSourceKey(String key) {
        dataSourceKeys.remove(key);
    }



















}

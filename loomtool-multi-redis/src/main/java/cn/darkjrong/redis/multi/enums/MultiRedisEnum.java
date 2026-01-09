package cn.darkjrong.redis.multi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * redis多数据源枚举
 *
 * @author Rong.Jia
 * @date 2026/01/09
 */
@Getter
@AllArgsConstructor
public enum MultiRedisEnum {

    MASTER("master"),
    REDIS_PREFIX("spring.redis"),


    ;

    private final String value;








}

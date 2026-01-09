package cn.darkjrong.redis.multi.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 部署模式
 *
 * @author Rong.Jia
 * @date 2024/09/03
 */
@Getter
@AllArgsConstructor
public enum DeploymentMode {

    // 部署方式(单机:standalone,哨兵:sentinel,集群:cluster)

    standalone,sentinel,cluster





}

package cn.darkjrong.redis.multi.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 缓存信息
 * </p>
 *
 * @author Rong.Jia
 * @since 2024-08-30
 */
@Data
public class CacheInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
    * 项目ID
    */
    private Long projectId;

    /**
    * 类型(redis)
    */
    private String cacheType;

    /**
    * 部署方式(单机:standalone,哨兵:sentinel,集群:cluster)
    */
    private String deploymentMode;

    /**
    * 名称
    */
    private String name;

    /**
    * 编码
    */
    private String code;

    /**
    * 服务器地址
    */
    private String serverAddresses;

    /**
    * 数据库名
    */
    private String databaseName;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码
    */
    private String password;

    /**
    * 集群名
    */
    private String clusterName;

    /**
     * 读取超时时长(默认:10000,单位:毫秒)
     */
    private Integer readTimeout;

    /**
     * 连接超时时长(默认:60000,单位:毫秒)
     */
    private Integer connectTimeout;

    /**
     * 最大空闲(默认:8)
     */
    private Integer maxIdle;

    /**
     * 最小空闲(默认:0)
     */
    private Integer minIdle;

    /**
     * 最大连接数(默认:16)
     */
    private Integer maxActive;



}

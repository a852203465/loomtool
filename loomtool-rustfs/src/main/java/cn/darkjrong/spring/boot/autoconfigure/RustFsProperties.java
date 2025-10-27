package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * RustFs属性
 *
 * @author Rong.Jia
 * @date 2025/10/27
 */
@Slf4j
@Data
@Configuration
@ConfigurationProperties("loom.rustfs")
public class RustFsProperties {

    /**
     *  是否开启, 默认:false
     */
    private boolean enabled = Boolean.FALSE;

    /**
     * 对象存储服务的URL
     */
    private String endpoint;

    /**
     * Access key就像用户ID，可以唯一标识你的账户。
     */
    private String accessKey;

    /**
     * Secret key是你账户的密码。
     */
    private String secretKey;

    /**
     *  bucket 名称
     */
    private String bucketName;












}

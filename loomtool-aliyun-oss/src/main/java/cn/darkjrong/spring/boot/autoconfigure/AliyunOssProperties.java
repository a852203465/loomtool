package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云OSS属性
 *
 * @author Rong.Jia
 * @date 2024/08/12
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.aliyun.oss")
public class AliyunOssProperties {

    /**
     * 是否启用，默认:false
     */
    private Boolean enabled = Boolean.FALSE;

    /**
     * 外网域名
     */
    private String endpoint;

    /**
     * 内网
     */
    private Intranet intranet = new Intranet();

    /**
     * ak
     */
    private String accessKeyId;

    /**
     * aks
     */
    private String accessKeySecret;

    /**
     * 图片压缩
     */
    private ThumbImage thumbImage;

    @Data
    public static class Intranet {

        /**
         * 是否使用内网模式上传
         * 开启：true, 关闭：false
         */
        private Boolean enabled = false;

        /**
         * 内网地址
         */
        private String endpoint;

    }

    @Data
    public static class ThumbImage {

        /**
         * 自动调节精度
         */
        private Boolean autoAccuracy = Boolean.FALSE;

        /**
         * 压缩后的文件最大值,单位KB
         * {@see autoAccuracy值为true,该值无效}
         */
        private Integer maxSize;

        /**
         * 发生压缩的文件大小,单位KB
         */
        private Integer minSize;






    }









}

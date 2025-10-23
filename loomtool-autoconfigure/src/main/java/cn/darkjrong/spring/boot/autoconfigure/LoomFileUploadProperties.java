package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 文件上传属性
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.multipart")
public class LoomFileUploadProperties {

    /**
     *  是否开启， 默认：false
     */
    private boolean enabled = false;

    /**
     * 路径， 默认：System.getProperty("user.dir") + "/data/tmp";
     */
    private String location;

    /**
     *  路径是否在当前项目的根目录生成
     */
    private Boolean isProjectRoot = Boolean.TRUE;

    /**
     * 文件大小限制，单位：M 默认： 10M
     */
    private Long maxFileSize = 10L;

    /**
     * 设置总上传数据总大小，单位：M 默认： 10M
     */
    private Long maxRequestSize = 10L;

    /**
     * 设置磁盘写入的限制，单位：M 默认： 20M
     */
    private Long fileSizeThreshold = 20L;


}

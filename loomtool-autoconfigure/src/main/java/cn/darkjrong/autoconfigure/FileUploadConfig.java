package cn.darkjrong.autoconfigure;

import cn.darkjrong.core.lang.constants.FileConstant;
import cn.darkjrong.spring.boot.autoconfigure.LoomFileUploadProperties;
import cn.hutool.core.util.StrUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;


/**
 * 文件上传配置
 *
 * @author Rong.Jia
 * @date 2021/07/07
 */
@Configuration
@ConditionalOnClass({DispatcherServlet.class, WebMvcConfigurer.class})
@ConditionalOnProperty(prefix = "loom.multipart", name = "enabled", havingValue = "true")
public class FileUploadConfig {

    private final LoomFileUploadProperties loomFileUploadProperties;

    public FileUploadConfig(LoomFileUploadProperties loomFileUploadProperties) {
        this.loomFileUploadProperties = loomFileUploadProperties;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {

        MultipartConfigFactory factory = new MultipartConfigFactory();

        //路径有可能限制
        File tmpFile = new File(getLocation());
        if (!tmpFile.exists()) {
            tmpFile.mkdirs();
        }

        DataSize fileSizeThreshold = DataSize.ofMegabytes(loomFileUploadProperties.getFileSizeThreshold());
        DataSize maxFileSize = DataSize.ofMegabytes(loomFileUploadProperties.getMaxFileSize());
        DataSize maxRequestSize = DataSize.ofMegabytes(loomFileUploadProperties.getMaxRequestSize());

        factory.setLocation(loomFileUploadProperties.getLocation());
        factory.setFileSizeThreshold(fileSizeThreshold);
        factory.setMaxFileSize(maxFileSize);
        factory.setMaxRequestSize(maxRequestSize);
        return factory.createMultipartConfig();
    }

    private String getLocation() {
        String path = FileConstant.TMP_DIR;
        if (StrUtil.isNotBlank(loomFileUploadProperties.getLocation())) {
            path = loomFileUploadProperties.getIsProjectRoot()
                    ? System.getProperty("user.dir") + loomFileUploadProperties.getLocation()
                    : loomFileUploadProperties.getLocation();
        }
        return path;
    }
}

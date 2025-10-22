package cn.darkjrong.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * FtpServer自动配置
 *
 * @author Rong.Jia
 * @date 2025/10/22
 */
@Configuration
@ComponentScan("cn.darkjrong.ftpserver")
@EnableConfigurationProperties(FtpServerProperties.class)
@ConditionalOnProperty(prefix = "loom.ftp.server", name = "enabled", havingValue = "true")
public class FtpServerAutoConfiguration {

    private final FtpServerProperties ftpServerProperties;

    public FtpServerAutoConfiguration(final FtpServerProperties ftpServerProperties) {
        this.ftpServerProperties = ftpServerProperties;
    }

    @Bean
    public FtpServerFactoryBean ftpServerFactoryBean() {
        final FtpServerFactoryBean factoryBean = new FtpServerFactoryBean();
        factoryBean.setProperties(this.ftpServerProperties);
        return factoryBean;
    }

}

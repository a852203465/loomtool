package cn.darkjrong.spring.boot.autoconfigure;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * RustFs自动配置类
 *
 * @author Rong.Jia
 * @date 2025/10/27
 */
@Configuration
@ConditionalOnProperty(prefix = "loom.rustfs", name = "enabled", havingValue = "true")
@EnableConfigurationProperties(RustFsProperties.class)
@ComponentScan("cn.darkjrong.rustfs")
public class RustFsAutoConfiguration {

    @Bean
    public RustFsFactoryBean rustFsFactoryBean(RustFsProperties rustFsProperties) {
        return new RustFsFactoryBean(rustFsProperties);
    }



}

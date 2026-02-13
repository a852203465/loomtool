package cn.darkjrong.spring.boot.autoconfigure;

import cn.darkjrong.multi.version.configuration.MultiVersionApiRegistrations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 多版本API自动配置
 *
 * @author Rong.Jia
 * @date 2026/02/13
 */
@Configuration
@EnableConfigurationProperties(MultiVersionApiProperties.class)
@ConditionalOnProperty(prefix = "loom.multi.version", name = "enabled", havingValue = "true")
@ComponentScan("cn.darkjrong.multi.version")
public class MultiVersionApiAutoConfiguration {

    @Bean
    public MultiVersionApiRegistrations apiVersionWebMvcRegistrations(MultiVersionApiProperties multiVersionApiProperties) {
        return new MultiVersionApiRegistrations(multiVersionApiProperties);
    }



}

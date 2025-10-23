package cn.darkjrong.spring.boot.autoconfigure;

import cn.hutool.extra.spring.EnableSpringUtil;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 自动配置
 *
 * @author Rong.Jia
 * @date 2021/07/07
 */
@Configuration
@EnableSpringUtil
@ComponentScan("cn.darkjrong.autoconfigure")
@EnableConfigurationProperties({
        LoomCorsProperties.class,
        LoomExecutorPoolProperties.class,
        LoomExecutorProperties.class,
        LoomFileUploadProperties.class,
        LoomScheduledProperties.class,
        LoomTrimStringProperties.class,
})
public class LoomAutoConfigure {








}

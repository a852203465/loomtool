package cn.darkjrong.spring.boot.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 跨域属性
 *
 * @author Rong.Jia
 * @date 2025/10/23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "loom.cors")
public class LoomCorsProperties {

    /**
     *  是否开启，默认：false
     */
    private boolean enabled = false;


}

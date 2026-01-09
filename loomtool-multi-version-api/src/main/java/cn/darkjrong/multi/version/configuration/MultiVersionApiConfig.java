package cn.darkjrong.multi.version.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 多版本api配置
 *
 * @author Rong.Jia
 * @date 2026/01/05
 */
@Configuration
public class MultiVersionApiConfig extends DelegatingWebMvcConfiguration {

    @Override
    public RequestMappingHandlerMapping createRequestMappingHandlerMapping() {
        return new MultiVersionApiHandlerMapping();
    }

}

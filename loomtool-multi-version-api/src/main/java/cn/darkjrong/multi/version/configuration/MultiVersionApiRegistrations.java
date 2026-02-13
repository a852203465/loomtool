package cn.darkjrong.multi.version.configuration;

import cn.darkjrong.spring.boot.autoconfigure.MultiVersionApiProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 多版本api注册
 *
 * @author Rong.Jia
 * @date 2026/02/13
 */
public class MultiVersionApiRegistrations implements WebMvcRegistrations {

    private final MultiVersionApiProperties multiVersionApiProperties;

    public MultiVersionApiRegistrations(MultiVersionApiProperties multiVersionApiProperties) {
        this.multiVersionApiProperties = multiVersionApiProperties;
    }

    @Override
    public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
        return new MultiVersionApiHandlerMapping(multiVersionApiProperties);
    }

}

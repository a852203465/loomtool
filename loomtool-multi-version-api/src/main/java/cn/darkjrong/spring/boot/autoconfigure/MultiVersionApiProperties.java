package cn.darkjrong.spring.boot.autoconfigure;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 多版本api属性
 *
 * @author Rong.Jia
 * @date 2026/02/13
 */
@Data
@Component
@ConfigurationProperties(prefix = "loom.multi.version")
public class MultiVersionApiProperties {

    /**
     * 是否开启，默认：false
     */
    private boolean enabled = false;

    /**
     * 请求头名
     */
    private String headerName = "X-API-VERSION";

    /**
     * 参数属性名
     */
    private String parameterName = "X-API-VERSION";


    public String getHeaderName() {
        return StrUtil.isBlank(headerName) ? "X-API-VERSION" : headerName;
    }

    public String getParameterName() {
        return StrUtil.isBlank(parameterName) ? "X-API-VERSION" : parameterName;
    }
}

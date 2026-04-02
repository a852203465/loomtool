package cn.darkjrong.spring.boot.autoconfigure;

import cn.darkjrong.multi.version.constants.MultiVersionConstant;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
    private String headerName = MultiVersionConstant.MULTI_VERSION_HEADER;

    /**
     * 参数属性名
     */
    private String parameterName = MultiVersionConstant.MULTI_VERSION;

    /**
     * 排除URL
     * */
    private List<String> excludes = new ArrayList<>();


    public String getHeaderName() {
        return StrUtil.isBlank(headerName) ? MultiVersionConstant.MULTI_VERSION_HEADER : headerName;
    }

    public String getParameterName() {
        return StrUtil.isBlank(parameterName) ? MultiVersionConstant.MULTI_VERSION : parameterName;
    }

    public List<String> getExcludes() {
        return CollUtil.isEmpty(excludes)
                ? MultiVersionConstant.SWAGGER
                : CollUtil.newArrayList(CollUtil.unionDistinct(MultiVersionConstant.SWAGGER, excludes));
    }
}

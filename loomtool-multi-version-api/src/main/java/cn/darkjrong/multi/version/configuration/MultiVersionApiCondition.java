package cn.darkjrong.multi.version.configuration;

import cn.darkjrong.spring.boot.autoconfigure.MultiVersionApiProperties;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * 版本匹配
 * @author Rong.Jia
 * @date 2026/01/04
 */
@Slf4j
public class MultiVersionApiCondition implements RequestCondition<MultiVersionApiCondition> {

    private final String apiVersion;
    private final MultiVersionApiProperties multiVersionApiProperties;

    public MultiVersionApiCondition(String apiVersion, MultiVersionApiProperties multiVersionApiProperties) {
        this.apiVersion = apiVersion;
        this.multiVersionApiProperties = multiVersionApiProperties;
    }

    @Override
    public MultiVersionApiCondition combine(MultiVersionApiCondition other) {
        return new MultiVersionApiCondition(other.apiVersion, multiVersionApiProperties);
    }

    @Override
    public MultiVersionApiCondition getMatchingCondition(HttpServletRequest request) {
        String headerVersion = request.getHeader(multiVersionApiProperties.getHeaderName());
        String paramVersion = request.getParameter(multiVersionApiProperties.getHeaderName());
//        String pathVersion = null;
//        String pathInfo = request.getPathInfo();
//        if (StrUtil.isNotBlank(pathInfo)) {
//            String[] parts = pathInfo.split("/");
//            if (ArrayUtil.isNotEmpty(parts)) {
//                pathVersion = parts[parts.length - 1];
//            }
//        }
        String version = StrUtil.isNotBlank(paramVersion)
                ? paramVersion
                : headerVersion;
        boolean defaultVersion = ObjectUtil.isAllEmpty(headerVersion, paramVersion);
        return defaultVersion ? null : (compareVersion(version, apiVersion) == 0 ? this : null);
    }

    @Override
    public int compareTo(MultiVersionApiCondition other, HttpServletRequest request) {
        return compareVersion(other.apiVersion, this.apiVersion);
    }

    private int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            throw new RuntimeException("compareVersion error:illegal params.");
        }
        return StrUtil.equals(version1, version2) ? 0 : -1;
    }
}

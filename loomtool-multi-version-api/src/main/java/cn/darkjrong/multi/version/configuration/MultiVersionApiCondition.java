package cn.darkjrong.multi.version.configuration;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 版本匹配
 * @author Rong.Jia
 * @date 2026/01/04
 */
@Slf4j
public class MultiVersionApiCondition implements RequestCondition<MultiVersionApiCondition> {

    private final String apiVersion;

    public MultiVersionApiCondition(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    @Override
    public MultiVersionApiCondition combine(MultiVersionApiCondition other) {
        return new MultiVersionApiCondition(other.apiVersion);
    }

    @Override
    public MultiVersionApiCondition getMatchingCondition(HttpServletRequest request) {
        String xApiVersion = request.getHeader("x-api-version");
        String versionParam = request.getParameter("x-api-version");
        String pathVersion = null;
        String pathInfo = request.getPathInfo();
        if (StrUtil.isNotBlank(pathInfo)) {
            String[] parts = pathInfo.split("/");
            if (ArrayUtil.isNotEmpty(parts)) {
                pathVersion = parts[parts.length - 1];
            }
        }
        String[] strings = {pathVersion, versionParam, xApiVersion};
        String version = Arrays.stream(strings)
                .filter(StrUtil::isNotBlank)
                .findFirst()
                .orElse(null);
        return compareVersion(version, apiVersion) == 0 ? this : null;
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

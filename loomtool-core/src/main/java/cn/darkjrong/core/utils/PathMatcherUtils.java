package cn.darkjrong.core.utils;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * 路径匹配器工具类
 *
 * @author Rong.Jia
 * @date 2022/12/01
 */
public class PathMatcherUtils {

    private static final PathMatcher PATH_MATCHER = new AntPathMatcher();

    /**
     * 匹配器
     *
     * @param patterns 规则
     * @param path 路径
     * @return boolean
     */
    public static boolean matcher(String path, List<String> patterns) {
        for (String url : patterns) {
            if (PATH_MATCHER.match(url, path)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }

}

package cn.darkjrong.multi.version.constants;

import java.util.Arrays;
import java.util.List;

/**
 * 多版本常数
 *
 * @author Rong.Jia
 * @date 2026/02/27
 */
public class MultiVersionConstant {

    public static final List<String> SWAGGER = Arrays.asList("/webjars/**", "/doc.html", "/swagger-resources/**", "/*/v3/api-docs", "/actuator/**");

    public static final String MULTI_VERSION_HEADER = "Accept-Version";

    public static final String MULTI_VERSION = "version";


}

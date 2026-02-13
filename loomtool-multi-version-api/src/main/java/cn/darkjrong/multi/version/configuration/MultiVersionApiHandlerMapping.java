package cn.darkjrong.multi.version.configuration;

import cn.darkjrong.multi.version.annotation.ApiVersion;
import cn.darkjrong.spring.boot.autoconfigure.MultiVersionApiProperties;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 多版本API处理程序
 *
 * @author Rong.Jia
 * @date 2026/01/05
 */
public class MultiVersionApiHandlerMapping extends RequestMappingHandlerMapping {

    private final MultiVersionApiProperties multiVersionApiProperties;

    public MultiVersionApiHandlerMapping(MultiVersionApiProperties multiVersionApiProperties) {
        this.multiVersionApiProperties = multiVersionApiProperties;
    }

    @Override
    public RequestCondition<?> getCustomTypeCondition(@NonNull Class<?> handlerType) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
        return null == apiVersion
                ? super.getCustomTypeCondition(handlerType)
                : new MultiVersionApiCondition(apiVersion.value(), multiVersionApiProperties);
    }

    @Override
    public RequestCondition<?> getCustomMethodCondition(@NonNull Method method) {
        ApiVersion apiVersion = AnnotationUtils.findAnnotation(method, ApiVersion.class);
        return null == apiVersion
                ? super.getCustomMethodCondition(method)
                : new MultiVersionApiCondition(apiVersion.value(), multiVersionApiProperties);
    }





}
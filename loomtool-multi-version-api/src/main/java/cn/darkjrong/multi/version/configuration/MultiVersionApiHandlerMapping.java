package cn.darkjrong.multi.version.configuration;

import cn.darkjrong.multi.version.annotation.ApiVersion;
import cn.darkjrong.spring.boot.autoconfigure.MultiVersionApiProperties;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class MultiVersionApiHandlerMapping extends RequestMappingHandlerMapping {

    private final MultiVersionApiProperties multiVersionApiProperties;

    public MultiVersionApiHandlerMapping(MultiVersionApiProperties multiVersionApiProperties) {
        this.multiVersionApiProperties = multiVersionApiProperties;
    }

//    @Override
//    public RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
//        if (!AnnotatedElementUtils.hasAnnotation(method, RequestMapping.class)
//                || AnnotatedElementUtils.hasAnnotation(handlerType, FeignClient.class)) {
//            return super.getMappingForMethod(method, handlerType);
//        }
//        RequestMappingInfo mapping = super.getMappingForMethod(method, handlerType);
//
//        ApiVersion methodAnnotation = AnnotationUtils.findAnnotation(method, ApiVersion.class);
//        if (ObjectUtil.isNotNull(methodAnnotation)) {
//            if (StrUtil.isBlank(methodAnnotation.value())) {
//                log.error("【{}】method ApiVersion annotation is empty", method.getName());
//                throw new BeanCreationException(String.format("【%s】method ApiVersion annotation is empty", method.getName()));
//            }
//            return MultiVersionApiCondition.paths(methodAnnotation.value())
//                    .customCondition(getCustomMethodCondition(method))
//                    .options(getBuilderConfiguration())
//                    .build().combine(mapping);
//        } else {
//            ApiVersion typeAnnotation = AnnotationUtils.findAnnotation(handlerType, ApiVersion.class);
//            if (ObjectUtil.isNotNull(typeAnnotation)) {
//                if (StrUtil.isBlank(methodAnnotation.value())) {
//                    log.error("【{}】handlerType ApiVersion annotation is empty", handlerType.getName());
//                    throw new BeanCreationException(String.format("【%s】handlerType ApiVersion annotation is empty", handlerType.getName()));
//                }
//                return RequestMappingInfo.paths(typeAnnotation.value())
//                        .customCondition(getCustomTypeCondition(handlerType))
//                        .options(getBuilderConfiguration())
//                        .build().combine(mapping);
//            }
//        }
//        return mapping;
//    }

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
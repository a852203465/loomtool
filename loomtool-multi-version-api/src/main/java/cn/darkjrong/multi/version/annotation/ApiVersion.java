package cn.darkjrong.multi.version.annotation;

import org.springframework.web.bind.annotation.Mapping;

import java.lang.annotation.*;

/**
 * 版本控制注解
 *
 * @author Rong.Jia
 * @date 2026/01/04
 */
@Documented
@Mapping
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiVersion {

    /**
     * 版本号
     * @return 版本号
     */
    String value() default "";



}
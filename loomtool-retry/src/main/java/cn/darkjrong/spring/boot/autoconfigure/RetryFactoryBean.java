package cn.darkjrong.spring.boot.autoconfigure;

import cn.darkjrong.retry.RetryTemplate;
import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;

/**
 * 重试工厂类
 *
 * @author Rong.Jia
 * @date 2025/09/28
 */
@Slf4j
public class RetryFactoryBean implements FactoryBean<RetryTemplate>, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private RetryTemplate retryTemplate;
    private final RetryPlanProperties retryPlanProperties;

    public RetryFactoryBean(RetryPlanProperties retryPlanProperties) {
        this.retryPlanProperties = retryPlanProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Integer maxAttempts = retryPlanProperties.getMaxAttempts();
        List<RetryPlanProperties.ScheduleProperties> plans = retryPlanProperties.getPlans();
        if (CollUtil.isEmpty(plans)) {
            log.error("*********,重试计划配置不能为空");
            throw new BeanCreationException("重试计划配置不能为空");
        }
        if (maxAttempts != plans.size()) {
            log.error("*********,重试计划数量与最大重试次数不一致");
            throw new BeanCreationException("重试计划配置数量与最大重试次数不一致");
        }
        retryTemplate = new RetryTemplate(retryPlanProperties);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public RetryTemplate getObject() {
        return this.retryTemplate;
    }

    @Override
    public Class<?> getObjectType() {
        return RetryTemplate.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

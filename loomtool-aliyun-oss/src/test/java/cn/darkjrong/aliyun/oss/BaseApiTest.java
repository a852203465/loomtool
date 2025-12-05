package cn.darkjrong.aliyun.oss;

import cn.darkjrong.spring.boot.autoconfigure.AliyunOssFactoryBean;
import cn.darkjrong.spring.boot.autoconfigure.AliyunOssProperties;
import com.aliyun.oss.OSS;

/**
 * 测试类初始化
 *
 * @author Rong.Jia
 * @date 2024/08/12
 */
public class BaseApiTest {

    protected static AliyunOssProperties aliyunOSSProperties;
    protected static OSS ossClient;

    static {
        aliyunOSSProperties = new AliyunOssProperties();
        aliyunOSSProperties.setEndpoint("http://oss-cn-shenzhen.aliyuncs.com");

        AliyunOssProperties.Intranet intranet = new AliyunOssProperties.Intranet();
        intranet.setEnabled(Boolean.FALSE);
        aliyunOSSProperties.setIntranet(intranet);
        aliyunOSSProperties.setAccessKeyId("123131");
        aliyunOSSProperties.setAccessKeySecret("123213121221");
        AliyunOssFactoryBean aliyunOSSFactoryBean = new AliyunOssFactoryBean(aliyunOSSProperties);
        aliyunOSSFactoryBean.afterPropertiesSet();
        ossClient = aliyunOSSFactoryBean.getObject();
    }
















}

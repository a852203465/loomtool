package cn.darkjrong.spring.boot.autoconfigure;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * RustFs工厂类
 *
 * @author Rong.Jia
 * @date 2025/10/27
 */
@Slf4j
public class RustFsFactoryBean implements FactoryBean<S3Client>, InitializingBean {

    private S3Client s3Client;
    private final RustFsProperties rustFsProperties;

    public RustFsFactoryBean(RustFsProperties rustFsProperties) {
        this.rustFsProperties = rustFsProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        String endpoint = rustFsProperties.getEndpoint();
        String accessKey = rustFsProperties.getAccessKey();
        String secretKey = rustFsProperties.getSecretKey();
        String bucketName = rustFsProperties.getBucketName();

        Assert.notBlank(endpoint, "'endpoint' cannot be empty");
        Assert.notBlank(secretKey, "'secretKey' cannot be empty");
        Assert.notBlank(accessKey, "'accessKey' cannot be empty");
        Assert.notBlank(bucketName, "'bucketName' cannot be empty");

        endpoint = !StrUtil.startWith(endpoint, "http://")
                ? "http://" + endpoint
                : endpoint;

        StaticCredentialsProvider staticCredentialsProvider
                = StaticCredentialsProvider.create(AwsBasicCredentials.create(accessKey, secretKey));

        s3Client = S3Client.builder()
                .endpointOverride(URI.create(endpoint))
                .region(Region.US_EAST_1)
                .credentialsProvider(staticCredentialsProvider)
                .forcePathStyle(true)
                .build();
    }

    @Override
    public S3Client getObject() {
        return this.s3Client;
    }

    @Override
    public Class<?> getObjectType() {
        return S3Client.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}

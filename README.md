# loomtool 
JAVA开发工具包

该工具包不为重复造轮子,只为把项目中相同配置封装起来,好用的框架集中

## 1. 模块说明
```text
├─loomtool-autoconfigure                 -- 自动配置模块                    
├─loomtool-all                           -- 所有工具包引入模块            
├─loomtool-crontab                       -- CRON表达式模块     
├─loomtool-dependencies                  -- 工具包版本控制模块, 类型Spring Cloud dependencies             
├─loomtool-exceptions                    -- 全局异常模块               
├─loomtool-httpclient                    -- HTTP 调用模块                   
├─loomtool-jpa                           -- ORM(JPA)模块            
├─loomtool-mail                          -- 邮件模块       
├─loomtool-mybatis                       -- ORM(Mybatis)模块                          
├─loomtool-pager                         -- 分页模块                         
├─loomtool-redis                         -- Redis模块                         
├─loomtool-swagger                       -- Swagger模块                        
└─loomtool-swagger-gateway               -- Swagger 网关模块  
└─loomtool-core                          -- 核心包       
└─loomtool-minio                         -- minio模块   
└─loomtool-aliyun-oss                    -- 阿里云OSS模块  
└─loomtool-retry                         -- 重试模块
└─loomtool-redis-lock                    -- 分布式锁模块
└─loomtool-captcha                       -- 验证码模块
└─loomtool-i18n                          -- 多语言模块
└─loomtool-redis-limit                   -- redis限流模块
└─loomtool-xxl-job                       -- XXL-JOB
└─loomtool-xss                           -- XSS
└─loomtool-wrapper                       -- Request,Response包装
└─loomtool-multi-version-api             -- API多版本





 
 
 
```

## 2. 使用说明
    详见各个模块

## 3. 注意事项
    1. 需要使用那个模块直接引入即可，
    2. "loomtool-all"引入后无须引入其他模块   
    3. "loomtool-dependencies"引入后可自定义引入指定模块，无须指定版本
    4. "loomtool-swagger-gateway" 只可网关服务使用, 如：Spring Cloud Zuul, Spring Cloud Gateway

### 版本说明
| 版本号   | 描述                                                                                                                                                                            | 
|-------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 3.2.0 | 增加minio模块                                                                                                                                                                     |
| 4.0.0 | loomtool-mybatis模块中修改`BaseService`，`BaseServiceImpl` 的泛型顺序                                                                                                                   |
| 4.1.0 | loomtool-mybatis模块增加公有方法                                                                                                                                                     |
| 4.2.0 | `loomtool-dependencies`模块更正`loomtool-mail`引用                                                                                                                                |
| 4.3.0 | loomtool-mail 增加免密                                                                                                                                                           |
| 4.3.1 | swagger-gateway增加服务名称配置                                                                                                                                                       |
| 4.3.2 | minio模块"putObject"方法增加“baseDir”参数                                                                                                                                             |
| 4.3.3 | minio模块"putObject"方法修复“baseDir”参数拼接问题                                                                                                                                         |
| 4.3.4 | minio模块"putObject"方法传参问题                                                                                                                                                      |
| 5.0.0 | 增加 xxl-job模块                                                                                                                                                                  |
| 5.2.0 | jpa,mybatis模块的"Base"类 适配Oracle数据库                                                                                                                                             |
| 5.2.1 | loomtool-mail模块修改 '收件人'非必填                                                                                                                                                   |
| 5.2.2 | loomtool-minio模块引入okhttp依赖                                                                                                                                                   |
| 5.2.3 | loomtool-dependencies加入loomtool-core模块                                                                                                                                      |
| 5.2.4 | loomtool-autoconfigure中增加自动去除参数'字符串'类型前后空格                                                                                                                                   |
| 5.2.5 | 修改去除前后字符串，POST参数嵌套对象问题                                                                                                                                                        |
| 5.2.6 | RedisConfig修改连接工厂类                                                                                                                                                            |
| 5.3.0 | loomtool-exceptions模块增加全局异常处理逻辑, 增加基础异常类                                                                                                                                     |
| 5.3.1 | 处理线程池初始化问题，修改BaseCommon类问题                                                                                                                                                    |
| 6.0.0 | 增加license代码                                                                                                                                                                   |
| 6.0.1 | 对ResponseUtils.getResponse()增加重载                                                                                                                                              |
| 6.0.2 | 删除license相关                                                                                                                                                                   |
| 6.0.3 | ResponseVO重载方法                                                                                                                                                                |
| 6.0.4 | · 增加‘CustomMultipartFile’类，‘CommonMultipartFile’标记过时  <br/>· ‘PageDTO’增加‘startDate’,‘endDate’，并与‘startTime’,‘endTime’互相转换 <br/> · ResponseVO增加重载方法 <br/> · 对‘BaseService’增加方法 |
| 6.1.0 | 增加`loomtool-redis-lock`模块, 基于redis实现分布式锁,支持注解方式和工具类方式                                                                                                                        |
| 6.1.1 | 更新mybatis版本(3.5.3.2)                                                                                                                                                          |
| 6.1.2 | 关闭mybatis 分页插件的分页SQL校验                                                                                                                                                        |
| 6.1.3 | `loomtool-exceptions`增加捕获`ConstraintViolationException`                                                                                                                      |
| 6.2.0 | 简化`loomtool-exceptions`,把其他放入core中                                                                                                                                           |
| 6.3.0 | 增加`loomtool-aliyun-oss`模块,删除`loomtool-xxl-job`模块                                                                                                                            |
| 6.4.0 | 增加`loomtool-redis-limit`,`loomtool-retry`, `loomtool-i18n`, `loomtool-captcha`, `loomtool-xxl-job`模块,优化`loomtool-autoconfigure`模块,支持小于Spring Boot 3.0                   |





























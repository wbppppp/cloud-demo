# 启动nacos
## 切换盘符
> cd E:\package\nacos\bin

## 执行命令
> startup.cmd -m standalone

## 访问
> http://localhost:8848/nacos


# nacos配置中心

## 如何实现动态刷新

（1）在controller上使用@RefreshScope注解，用@value("${XXX}")引入配置 =》这样在该controller下引入的配置即可以进行动态刷新

（2）使用@Component @ConfigurationProperties来创建spring配置bean(com.example.order.properties.OrderProperties) => 以此配置批量绑定在nacos下，可以无需使用@RefreshScope就能实现批量动态刷新，并且在该bean下引入的配置都可全局动态刷新


## 配置项优先级

本地及Nacos配置中心共同加载顺序为：

bootstrap.yaml \
bootstrap.properties \
bootstrap-{profile}.yaml \
bootstrap-{profile}.properties \
application.yaml\
application.properties\
application-{profile}.yaml\
application-{profile}.properties\
nacos配置中心共享配置（通过spring.cloud.nacos.config.shared-configs指定）\
Nacos配置中心该服务配置（通过spring.cloud.nacos.config.prefix和spring.cloud.nacos.config.file-extension指定）\
Nacos配置中心该服务-{profile}配置（通过spring.cloud.nacos.config.prefix和spring.cloud.nacos.config.file-extension、以及spring.profiles.active指定）\


> 因此，配置生效覆盖关系： 对于key名相同，后加载会覆盖掉前加载，故而最终为后加载的配置项生效！ 对于key名不同，则直接生效（会加载，但不会被覆盖）；


# OpenFeign

## 启动注解

@EnableFeignClients @FeignClient

## 超时时间设置
> 1.spring配置，见application-feign.yaml（但是此种方式不支持动态变更，每次变化还需要更改代码）



## 重试机制
 
> 默认是首次重试间隔100ms，然后下次间隔100*1.5ms，当达到最大1s间隔时，每间隔1s重试一次，最大重试5次
> @Bean
> public Retryer retryer() {
>        return new Retryer.Default();// feign请求重试机制
> }

## 请求拦截器

> 1、如果只想对某个服务添加拦截器，那么可以在application-feign.yaml在指定的客户端上配置request-interceptor
> 
> 2、如果想所有的客户端都生效，那么可以用@Component标注在拦截器上，将拦截器bean交给ios管理即可，见com.example.order.interceptor.XTokenRequestInterceptor


## Fallback：兜底返回(熔断)
> 1. 创建xxxFeignClient的实现类xxxFeignClientFallBack，并对方法进行实现
> 2. 在xxxFeignClient的@FeignClient(fallback=xxxFeignClientFallBack.class)
> 3. application-feign.yaml配置文件中启用sentinel
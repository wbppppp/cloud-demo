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

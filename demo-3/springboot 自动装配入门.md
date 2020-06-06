#springboot 自动装配原理
##引入依赖
- spring-boot-starter-web
##自动配置类
- spring-boot-autoconfigure项目，提供了大量框架的自动配置
- xxxAutoConfiguration类
- spring boot启动时，SpringFactoriesLoader 读取META-INF/spring.factories
- org.springframework.boot.autoconfigure.EnableAutoConfiguration=
##条件注解
- @Conditional
- @Profile
##文件中配置属性，对应配置属性类
- @ConfigurationProperties
- @EnableConfigurationProperties
##内置starter
- spring-boot-starter-xxx 非常多
##自定义starter
- xxx-spring-boot-starter
##springboot扩展机制
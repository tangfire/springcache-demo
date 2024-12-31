# Spring Cache



Spring Cache是Spring提供的一个缓存框架，基于AOP原理，实现了基于注解的缓存功能，只需要简单地加一个注解就能实现缓存功能，对业务代码的侵入性很小。

## 参考文章

[Spring cache的使用](https://blog.csdn.net/qq_46637011/article/details/142032536)


## 1. 常用的 Spring Cache 注解

- @EnableCaching：开启缓存功能，通常加在启动类上
- @Cacheable：将方法返回值缓存，适用于读取操作。(在方法执行前先查询缓存中是否有数据，如果有数据，则直接返回缓存数据；如果没有缓存数据，调用方法并将方法返回值放到缓存中)
- @CacheEvict：清除缓存，常用于删除或更新数据时。
- @CachePut：每次调用方法时都将返回值缓存，适用于更新缓存。
- @Caching：组合多个缓存注解，以便同时执行多个缓存操作。
- @CacheConfig：用于配置类的统一缓存配置，如指定缓存的名称等。


## 2. Spring Cache 的使用

### 2.1 引入依赖

在使用 Spring Cache 之前，需要在项目的 pom.xml 中引入相关依赖（例如，使用 Redis 作为缓存时需引入 Spring Data Redis 的依赖）。

```xml
  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-cache</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```


### 2.2 启动缓存功能

在 Spring Boot 应用的启动类上添加 @EnableCaching 注解，开启缓存支持。

```java

@Slf4j
@SpringBootApplication
@EnableCaching // 开启缓存注解功能
public class CacheDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(CacheDemoApplication.class,args);
        log.info("项目启动成功...");
    }
}


```

## 3. Spring Cache 的工作原理

### 3.1 基于AOP

Spring Cache 是基于 Spring 的 AOP 原理实现的。在执行被缓存的方法之前，代理对象会先检查缓存中是否已经有数据，如果有则直接返回缓存数据，不再执行方法。

### 3.2 缓存存储和管理

如果缓存中没有数据，则调用实际方法执行，并将方法的返回值存储到缓存中，供下次调用时使用。

### 3.3 注解作用

通过注解（如 @Cacheable 等），开发者无需显式地编写缓存逻辑，Spring Cache 自动处理缓存的存取和失效操作。


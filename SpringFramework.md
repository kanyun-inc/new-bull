www.yuanfudao.com

# Spring / Spring Boot 框架

---

## 先修要求

* Java 基础
 * 注解
 * 反射
 * 动态代理

---

## 学习目标

* 了解 Spring 框架的主要功能
 * 了解 IOC 的概念和实现机制
 * 了解 AOP 的概念和实现机制
* 了解 Spring Boot 框架的基本功能
 * Profile 与环境配置
 * 自动配置 AutoConfiguration

---

## 阅读材料

阅读材料中有涉及 XML 配置的部分，比如 Spring 的 XML 配置，AOP 的 XML 配置等等，可以跳过。

* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
 * II. Getting Started
  * 11. Developing Your First Spring Boot Application
* [Spring Core Reference](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html)
 * 1. The IoC container
 * 5. Aspect Oriented Programming with Spring
 * 6. Spring AOP APIs
* [Spring Boot Reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle)
 * III. Using Spring Boot
 * IV. Spring Boot features (第 24, 25, 46 节)
* [Spring Boot 启动原理分析](https://blog.csdn.net/hengyunabc/article/details/50120001)

---

## 讨论大纲

* IOC
 * IOC 解决了什么问题?
 * 哪些场景下适合 IOC
 * Spring IOC 原理
  * 从 @Component 到 @Autowired，Spring 做了什么?
* AOP
 * AOP 解决了什么问题?
 * 哪些场景下适合 IOC
 * Spring AOP 原理
  * 动态代理
  * CGLIB
* Spring Boot
 * Spring Boot 的启动流程
  * SpringApplicationRunListener 中每个方法的处理过程?
 * AutoConfiguration 过程
 * Spring Boot 的配置系统接受哪些配置方式?
 * 如何写一个 AutoConfiguration

---

## 练习

实现一个自动在数据库中记录日志的 @DbLog 注解。项目在 lab/Spring 中，分为 lab/Spring/commons-db-log 和 lab/Spring/commons-db-log-demo 两个项目。

需要大家完成 lab/Spring/commons-db-log 中的代码，并在 commons-db-log-demo 中跑通流程。

commons-db-log:

* 完成 DbLogAspect
 * 增加相应的 JoinPoint 方法
 * 获取上下文中的 className 和 methodName
 * 调用 dbLogStorage 写数据库
* 完成 DbLogAutoconfiguration
 * 通过 @Bean 注解，在 context 中注入 DbLogAspect

commons-db-log-demo:

* 引入 commons-db-log
* Context 注入一个 JdbcTemplate 提供给 DbLogAutoconfiguration
* 使用 @DbLog 来记录方法的执行日志

---

## 深入学习

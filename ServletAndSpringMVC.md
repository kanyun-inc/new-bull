www.yuanfudao.com

# Servlet和SpringMVC框架

---

## 先修要求

---

## 学习目标
* 了解RESTful
* 了解Servlet的概念
* 了解SpringMVC框架
* 了解Application Server的概念
* 了解SpringMVC的反应式框架
* 了解Java EE容器

---

## 阅读材料

* [Richardson Maturity Model](https://martinfowler.com/articles/richardsonMaturityModel.html) 描述REST的多个层次 [0.5h]

* [Server-Side Web Framework](https://developer.mozilla.org/zh-CN/docs/learn/Server-side/First_steps/Web_frameworks) 介绍一个服务端Web框架的作用 [0.5h]

* [Jetty 的工作原理](https://www.ibm.com/developerworks/cn/java/j-lo-jetty/index.html) Jetty是一个Servlet容器，文中介绍了Jetty的工作原理，可以跳过介绍Jboss和Tomcat的部分 [1h]

* [Spring Framework Documentation - Web on Servlet Stack](https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#spring-web)  Spring MVC文档，但是内容比较细，主要看1.2, 1.4, 1.5这几节 [1h]

* [Spring Framework Documentation - Web on Reactive Stack](https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#spring-webflux) Spring MVC Reactive文档，了解一下1.1节 [0.5h]

---

## 讨论大纲

---
### RESTful

* RESTful API设计风格
    * 统一接口 Uniform Interface
        * Identification of resources
        * Manipulation of resources through representations
        * Self-descriptive messages
        * Hypermedia as the engine of application state (HATEOAS)
    * 无状态 Stateless Communication
    * 可缓存 Explicit Caching
    * 客户端服务器分离 Client-Server Architecture
    * 分层系统 Layered System
* REST成熟度模型
* Cookie 和 Session

---

### 服务端Web框架
* MVC模式
* 核心作用
    * 路由
    * 模板
    * 易于处理请求
* 常用框架
    * Ruby on Rails 
    * Node.js Express/Koa
    * Structs, Spring MVC, Spark

---
### Servlet

* 定义
    * J2EE规范
    * 一组接口
    * 扩展Web服务的功能
* 生命周期 init/service/destroy
* HttpServlet
    * doGet/doPost/doPut/doDelete/...
    * HttpServletRequest/HttpServletResponse

---
### Jetty

* Jetty是一个Servlet容器，同样的还有Tomcat和Undertow
* Handler设计是Jetty的核心
* Filter
    * 责任链

<img src="https://www.eclipse.org/jetty/documentation/9.4.x/images/jetty-high-level-architecture.png">

---
### Spring MVC 路由

* HandlerMapping接口
    * `HandlerExecutionChain getHandler(request)`
* 一些Mapping
    * `SimpleUrlHandlerMapping`
    * `RequestMappingHandlerMapping`
    * `BeanNameUrlHandlerMapping`

---
### Spring MVC路由注册和匹配
* 注册路由，在创建Bean的时候注册所有带@ReqquestMapping注解的方法
```
    registerHandlerMethod:264, AbstractHandlerMethodMapping 
    detectHandlerMethods:250, AbstractHandlerMethodMapping 
    initHandlerMethods:214, AbstractHandlerMethodMapping 
    initializeBean:1624, AbstractAutowireCapableBeanFactory
```
* 根据路径匹配
```
    lookupHandlerMethod:343, AbstractHandlerMethodMapping 
    getHandlerInternal:314, AbstractHandlerMethodMapping 
    getHandler:1160, DispatcherServlet 
    doDispatch:940, DispatcherServlet 
```
---
### Spring MVC 参数处理
* 获取参数
    * RequestParam
    * RequestHeader
    * PathVariable
* 类型转换 
    * Converter 
    * Formatter

---
### Spring MVC 返回值处理
* RequestMappingHandlerMapping 
    * 会选择一个HandlerMethodReturnValueHandler来处理方法的返回值
* REST接口
    * 如果标记了@ResponseBody，说明我们希望把方法的返回值直接作为response
    * RequestResponseBodyMethodProcessor.writeWithMessageConverters()
* ModelAndView
    * 默认情况下，会认为返回值是一个view name
    * ViewResolver
    * 使用model来渲染view

---
### Spring MVC 异常处理
* HandlerExceptionResolver定义了异常处理
    * @ResponseStatus 设置一个exception所对应的错误码
    * @ExceptionHandler 自定义一个异常处理方法
    * @ControllerAdvice 设置一个bean作为全局异常处理
* commons-rest里定义的异常处理

---
### Spring MVC 拦截器

* 拦截器 Interceptor
    * 与Filter类似
    * 在DispatcherServlet内部执行，有更多的上下文信息

---

### Spring MVC 整体流程

<img src="https://terasolunaorg.github.io/guideline/1.0.1.RELEASE/en/_images/RequestLifecycle.png">

---

### Spring MVC 异步接口
* Web客户端如何从服务端获取更新
    * Polling
    * Long Polling
    * Streaming
    * Websocket
* Servlet 3.0开始支持异步请求
    * Callable
    * DeferredResult
    * ResponseBodyEmitter

---

### Spring Webflux
* Spring MVC 5.0开始支持Webflux
    * 反应式编程 Reactive Programming
    * 非阻塞IO
    * 基于Servlet 3.1以上的容器，或者是其他非阻塞IO库
    * 使用Reactive库
* Webflux必须依赖非阻塞IO吗？
<img src="https://docs.spring.io/spring/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/images/webflux-overview.png">

---

## 练习

* 以lab中的hello-jetty项目为基础，基于jetty来实现一个类似Spring MVC的简单框架 
    * 实现基于注解的路由，使用自定义的MyController, MyRequestMapping等注解
    * 实现获取request中的参数，且可以自动转化到常用类型如Int/Double。不需要支持路径中的参数。
    * 实现输出自动格式化为json
    * 实现简单的错误处理，比如抛一个BadRequestException，要返回400

* 下载https://spring.io/guides/gs/rest-service/，跟踪DispatchServlet中处理请求的步骤
    * 了解HandlerExecutionChain mappedHandler和HandlerAdapter ha在处理过程中赋了什么值？
    * 了解返回结果是如何处理成为Response的
    * 如果处理过程中抛了RuntimeException，处理流程是怎么样的？尝试自定义一个错误页面

---

## 深入学习

* [Tomcat 系统架构与设计模式](https://www.ibm.com/developerworks/cn/java/j-lo-tomcat1/index.html)

* [Spring MVC DispatcherServlet详解](https://zhuanlan.zhihu.com/p/22420952)

www.yuanfudao.com

# 设计模式与系统架构

---

## 先修要求

---

## 学习目标
* 了解面向对象设计的基本原则
* 了解设计模式
* 了解系统架构的概念
* 了解六边形架构
* 了解微服务架构

---

## 阅读材料
* [敏捷软件开发 - 原则、模式和实践](https://book.douban.com/subject/1140457/) 第二部分，主要介绍了面向对象设计中的SOLID原则 [1h]
* [Uncle Bob对SRP的解释](https://8thlight.com/blog/uncle-bob/2014/05/08/SingleReponsibilityPrinciple.html) 深入理解单一职责原则 
* [设计模式](http://www.runoob.com/design-pattern/design-pattern-tutorial.html) 对于各个OO设计模式的一个简介，比GOF简单一些 [3h]
* [六边形架构](https://my.oschina.net/xuemingdeng/blog/731913) 重点了解一下六边形架构的概念 
* [浅谈命令查询职责分离(CQRS)模式](http://www.cnblogs.com/yangecnu/p/Introduction-CQRS.html) 
* [微服务架构的优势与不足](http://blog.daocloud.io/microservices-1/) 这个是Chris Rechardson微服务系列的第一篇，整体介绍了微服务架构
---

## 讨论大纲

---

### 面向对象编程

* 软件在本质上是复杂的
* 编程范式
    * 过程式编程
    * 数据隐藏
        * 按模块划分程序，数据隐藏在模块之中
        * C语言里可以通过单独定义文件来实现数据隐藏
    * 数据抽象
        * 自定义类型，包装数据和其行为
    * 面向对象编程
        * 对于自定义类型实现了封装、继承、多态
* Alan Key提出了“面向对象编程”这个术语，但在他看法中OOP的核心是消息传递
    * `encapsulated servers exchanging non-command messages`

---

### 单一职责原则 Single Responsiblity Principle

* `一个类，只有一个引起它变化的原因`
    * “职责”指的是变化的原因 (a reason for change)
    * 如何确定变化？
    * 变化依赖于具体的需求，并不是所有“可能的变化”
* 高内聚，低耦合
* 可能导致的问题？

---

### 开放封闭原则 Open Close Principle

* `软件实体（类、模块、函数等等）应该是可以扩展的，但是不可修改的。`
* open for extension, closed for modification
* 实现方式
    * 依赖于接口而不是实现
    * 只能针对能够预测到的变化
    * 数据驱动
* 封闭是有成本的
    * 假设变化不会发生，等发生的时候再创建抽象  
    * 刺激变化尽早的发生
* 可能导致的问题？

---

### Liskov替换原则 Liskov Substitution Principle

* `子类型必须能够替换掉父类型`
* 一个模型的有效性依赖于使用场景！
    * `所有模型都是错的，但有些是有用的`
    * IS-A关系是针对特定的场景而言的
    * 用可替换性来检验子类型的正确定义
* 基于契约的设计
* 继承 vs 组合
* 可能导致的问题？

---

### 依赖倒置原则 Dependency Inversion Principle

* `高层模块不应该依赖低层模块，二者都应该依赖其抽象；抽象不应该依赖细节，细节应该依赖抽象`
* 依赖于抽象
    * 不持有具体类的引用
    * 不从具体类派生
    * 不要override基类中已实现的方法
* 依赖注入 vs 依赖倒置
* 可能导致的问题？

---

### 接口隔离原则 Interface Segregation Principle

* `不应该强迫客户依赖于它们不用的方法`
* 使用多个专用的接口，而不是用单一的总接口
    * 避免fat interface
    * 选择合适的粒度
* 可能导致的问题？

---

### 最少知识原则 Least Knowledge Princine

* `一个对象应该对其他对象保持最少的了解`
* 一种定义方式：只使用一个.运算
* 减少类之间的耦合
* 可能导致的问题？

---

### 思考

* SOLID原则不是最终目的！
* 一些别的原则
    * 高内聚，低耦合
    * KISS
    * DIY
    * 奥卡姆剃刀
* 目标
    * 可读性
    * 可维护性
    * 可扩展性
    * 可测试性
    * 。。。

---

### 设计模式

* GOF
    * 提出了模式的四要素 "名称-问题-解决方案-效果"
    * 提出了OO设计模式的核心原则
        * 面向接口编程，而不是具体的实现
        * 组合优于继承
    * 具体的23个设计模式只是对常用模式的一些总结
* 提供了更加方便的交流方式
* 很多模式是特别针对OO编程的，对于其他编程范式是无用的
* 不要为了设计模式而设计模式！

---

### 设计模式 - 创建型模式

* 工厂模式 Factory
* 抽象工厂模式 Abstract Factory
    * 创建工厂，生产一系列相关的对象
* 原型模式 Prototype
    * 通过拷贝原型创建新的对象
* 单例模式 Singleton
* 建造者模式 Builder
    * 建造复杂对象
* 依赖注入 Dependency Injection

---

### 设计模式 - 结构型模式

* 外观模式 Facade
    * 简化接口，隐藏复杂性
* 装饰模式 Decorator
    * 包装对象来增加功能
* 适配器模式 Adapter
    * 处理不兼容的接口
* 代理模式 Proxy
    * 相同的接口，但是可以增加控制
* 桥接模式 Bridge
    * 通过分离抽象和实现，来提供多个维度的变化

---

### 设计模式 - 行为型模式

* 命令模式 Command
    * 包装一组操作，实现不同的行为
* 策略模式 Strategy
    * 对同一个行为不同的实现方式
* 模板模式 Template
    * 定义好操作的骨架，实现通用的算法
* 迭代器模式 Iterator
    * 提供通用的迭代模式

---

### 设计模式 - 行为型模式

* 中介者模式 Mediator
    * 简化多个类之间的通信
* 观察者模式 Observer
    * 实现一对多的依赖关系
* 责任链模式 Chain of Responsibility
    * 解耦发送者与接受者
* 状态模式 State
    * 行为根据状态变动

---

### 架构

* 高层次的规划，比设计模式更加宏观
* 常用的架构模式
    * 分层架构 Layers
    * 六边形架构（接口与适配器） Ports And Adapters
    * 命令与查询分离 CQRS
    * 事件驱动架构 Event Driven
    * 微服务 Micro Service

---

### 六边形架构

* 又叫端口与适配器架构
    * 内层是应用的核心逻辑（领域层）
    * 外层提供接口和适配器，与外部交互
    * 端口包括输入和输出
* 代码的依赖只能**从外向内**
* 扩展为多层 - 洋葱架构


<img src="http://static.oschina.net/uploads/space/2016/0812/175225_9iWo_2681558.gif">

---

### 命令与查询分离 CQRS

* 使用分离的接口处理查询操作和修改操作
    * 对应不同的底层模型
    * 可以独立的优化查询和修改
    * 需要进行数据同步，一般使用事件驱动
* 事件溯源 Event Sourcing
    * 保留所有的事件，从而可以追踪所有的修改记录
    * 查询操作变成了对历史事件的遍历操作
* 问题？
    * 性能
    * 一致性
    * 开发成本

---

### 事件驱动架构

* 长时处理过程 Saga
    * 处理分布式事务
    * 基于编排 vs 基于协调者
    * 问题？

<img style="height:400px" src="https://microservices.io/i/data/Saga_Choreography_Flow.001.jpeg">

---

### 微服务

* 单体架构的困难
    * 复杂性
    * 难以部署
    * 资源冲突

<img style="height:400px" src="https://content.cdntwrk.com/files/aHViPTYzOTc1JmNtZD1pdGVtZWRpdG9yaW1hZ2UmZmlsZW5hbWU9aXRlbWVkaXRvcmltYWdlXzU4ZDlhMGQ1YjA4NTMucG5nJnZlcnNpb249MDAwMCZzaWc9OTI5YWJiZDM4NGQ3MGQzZDI1YTgzMmJkNmRkYjYzNjk%253D">

---

### 微服务

* 把应用拆分为一组互相关联的服务
    * 清晰的边界，降低每个服务的复杂度
    * 独立部署
    * 易于迭代
* 问题
    * 分布式系统的复杂性
    * 开发和测试的困难
    * 对运维的挑战
* 服务划分
    * 康威定律：系统结构与组织结构保持一致

---

## 练习

---

## 深入学习
* [Think you understand the Single Responsibility Principle?](https://hackernoon.com/you-dont-understand-the-single-responsibility-principle-abfdd005b137) 对于单一职责原则的另外一个很好的总结
* [微服务设计](https://book.douban.com/subject/26772677/) 对于微服务设计很好的讨论
* [Microservice Patterns](http://microservices.io/patterns/microservices.html) 介绍了用于微服务的多种设计模式
* [Bjarne Stroustrup - What is "Object-Oriented Programming"](http://www.stroustrup.com/whatis.pdf) BS对于OOP的定义和思考
* [设计模式：可复用面向对象软件的基础](https://book.douban.com/subject/1052241/) GOF，设计模式的代名词
* [面向模式的软件架构](https://book.douban.com/subject/25741382/) 和GOF同时代的模式大部头，区分了架构模式、设计模式和惯用法
* [陈皓：如此理解面向对象编程](https://coolshell.cn/articles/8745.html) 对OOP滥用的批判
* [Software Architecture Patterns](https://www.jianshu.com/p/29bfbbe1693f) 介绍了一些常用的软件架构
* [The Clean Architecture](https://blog.csdn.net/elinavampire/article/details/44745923) 解释了”干净架构”
* [领域驱动设计：软件核心复杂性应对之道/Domain-Driven Design: Tackling Complexity in the Heart of Software](https://book.douban.com/subject/26819666/) DDD的开山之作
* [实现领域驱动设计/Implementing Domain-Driven Design](https://book.douban.com/subject/25844633/) 对于DDD的一些实现有更加详细的讨论
* [Saga模式](https://microservices.io/patterns/data/saga.html) Saga模式是分布式系统和微服务中常用的实现分布式事务的方式

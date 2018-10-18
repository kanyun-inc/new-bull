www.yuanfudao.com

# 软件工程和自动化测试

---

## 先修要求

---

## 学习目标
* 了解各种自动化测试的概念
* 了解单元测试和TDD
* 了解Mock框架
* 了解软件工程的基本概念和流程
* 了解敏捷开发模式
* 了解Scrum流程

---

## 阅读材料

* [构建之法:现代软件工程](https://book.douban.com/subject/25965995/) 比较全面的介绍了软件工程，重点看第1、5、6、13章 [2h]
* [Scrum指南](https://www.scrumguides.org/docs/scrumguide/v2017/2017-Scrum-Guide-Chinese-Simplified.pdf) 结合我们的工作理解Scrum [1h]
* [JUnit单元测试使用详解](https://www.jianshu.com/p/314b494dfbb0) [0.5h]
* [Hamcrest匹配器框架](https://www.jianshu.com/p/848fd619238f) [0.5h]
* [Unit tests with Mockito - Tutorial](http://www.vogella.com/tutorials/Mockito/article.html) [中文版](https://juejin.im/entry/578f11aec4c971005e0caf82) [1h]

---

## 讨论大纲

---
### 软件工程

* 定义：软件开发、运营和维护的方法论
* 团队模式
* 开发流程
    * 瀑布模型
    * 迭代式开发
    * 敏捷流程
* 用户体验
<img src="https://ekiy5aot90-flywheel.netdna-ssl.com/wp-content/uploads/2013/07/segue-blog-waterfall-vs-agile-which-is-right-development-methodology-for-your-project.png">

---
### 敏捷开发

* 敏捷宣言
    * 个体和互动 高于 流程和工具
    * 工作的软件 高于 详尽的文档
    * 客户合作 高于 合同谈判
    * 响应变化 高于 遵循计划
* 极限编程
    * 结对编程
    * 测试驱动开发
    * 持续集成
<img src="https://d1fhzurqmm0rwe.cloudfront.net/wp-content/uploads/2017/08/agile-software-development-300x280.jpg">

---
### Scrum

* Scrum是一个框架
* Scrum的精髓在于小团队
* Scrum团队
    * 产品负责人、开发团队、Scrum Master
* Scrum事件
    * 以固定长度的Sprint为核心
    * 计划会议
    * 每日站会
    * 评审会议
    * 回顾会议
* 工件
    * 产品待办列表
    * Sprint待办列表
    * 增量
        * "完成“的定义

---
### 软件测试

* 功能测试
    * 单元测试
    * 功能测试
    * 集成测试
    * 系统测试
    * 端对端测试
* 非功能测试
    * 压力测试
    * 性能测试
    * 安全测试
* 其他分类
    * 冒烟测试
    * 验收测试
    * 回归测试
    * 白盒测试
    * 黑盒测试

<img src="https://jfiaffe.files.wordpress.com/2014/09/tests-pyramid.png">

---

### 深入JUnit和Hamcrest

* JUnit
    * Runner
    * 参数化测试
    * 规则
        * ErrorCollector
        * ExpectedException
        * TestName
        * TestWatcher
* Hamcrest
    * 对assertXXX方法的改进
    * 可以对多个matcher组合使用

---
### Mockito

* verify用来验证调用方式
* @Mock vs @Spy
* 什么时候用doReturn/doAnswer/doThrow ?
* 实现机制？
* 有哪些限制？
* Spring
    * @MockBean
    * @SpyBean

---
### 测试驱动开发

* 流程：红，绿，重构
* 规则
    * 除非是为了使一个失败的 unit test 通过，否则不允许编写任何产品代码
    * 在一个单元测试中，只允许编写刚好能够导致失败的内容（编译错误也算失败）
    * 只允许编写刚好能够使一个失败的 unit test 通过的产品代码

<img src="https://upload-images.jianshu.io/upload_images/279826-49f2f708aefb567f?imageMogr2/auto-orient/strip%7CimageView2/2/w/489/format/webp">

---

## 练习

* 挑一个熟悉的项目，找一个服务，为其添加单元测试，必要的情况下可以做一些重构

---

## 深入学习
* [硝烟中的Scrum和XP](http://www.infoq.com/cn/minibooks/scrum-xp-from-the-trenches)
* [Growing Object-Oriented Software, Guided by Tests](https://book.douban.com/subject/4156589/) 对于测试驱动开发讲解的很深入

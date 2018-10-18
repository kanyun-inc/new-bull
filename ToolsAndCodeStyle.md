www.yuanfudao.com

# 基本工具和代码规范

---

## 先修要求
* 了解Java语言，有一定的编程经验
* 对于Git/Maven/UNIX/Shell脚本有基本的使用经验

---

## 学习目标
* 理解Git工作模型，能够处理复杂的分支管理
* 理解Maven核心概念和规则，熟悉常用命令
* 了解Shell脚本的基本规则，熟悉常用工具
* 熟悉代码规范

---

## 阅读资料
* [Pro Git](https://git-scm.com/book/zh/v2) 前3章和7.7节，尤其是第3章*分支* [1h]
* [Maven实战](http://www.infoq.com/cn/minibooks/maven-in-action) 看第2、3章即可 [1h]
* [Bash Shell编程入门](https://www.jianshu.com/p/e1c8e5bfa45e) [1h]
* [Linux文本处理工具](http://linuxtools-rst.readthedocs.io/zh_CN/latest/base/03_text_processing.html) [1h]
* [公司的Java工程实践](https://wiki.zhenguanyu.com/Dev/JavaPractice) [1h]

---

## 讨论大纲

---

### Git
* Git如何存储数据
    * Snapshot vs Diff
    * 如何存储目录？
    * 如何保证数据完整性
* 文件状态
    * 三个区域
        * 工作区 working directory
        * 暂存区 staging area
        * 仓库  repo
    * git reset的不同类型

---

### Git Workflow
* branch和commit的关系
* 在gerrit上提交一个review，工作流程是怎么样的
    * 如果把分支A提到了分支B，会发生什么
* rebase和merge的机制和适用场景
* github的工作流程*
* 版本管理工具的变迁*

---

### Maven
* 作为项目的对象模型
    * 坐标与版本
    * SNAPSHOT版本的意义
* Maven的生命周期
    * 每个生命周期是一组有序的阶段
    * 解释mvn clean package的意义
* Maven依赖管理
    * 仓库的概念
    * 依赖范围
    * 如何确定依赖的版本
* Maven常用命令

---

### Shell脚本
* Shell基础
    * 变量
    * 运算符
    * 控制流
* Shell脚本的适用场景
* Shell脚本如何实现后台运行、重定向、并发等功能
* 常用文本处理工具

--- 

### UNIX 设计原则
* Controlling complexity is the essence of computer programming.
* Keep It Simple, Stupid!

---

### 代码规范
* 代码规范的原则和意义
    * Programs must be written for people to read, and only incidentally for machines to execute. - SICP
    * 保持代码风格的一致性比起追求最好的风格更加重要
    * 重构是保持代码质量的重要手段

---

## 练习
* 在lab中的http-request.log日志文件中
    * 计算各个url的调用超过10毫秒的次数，从大到小排序
    * 写一个shell脚本，找到调用请求次数最多的url，并展示这个url每小时的访问次数
* 打开lab中spring-boot-sample项目
    * 用mvn spring-boot:run命令来运行项目
    * 查看依赖树，找到各个依赖的版本是定义在什么地方的
    * 用IntelliJ打开这个项目，利用重构功能把HelloService类重构成接口+实现两个类，并把HelloService.Constant子类重构到一个独立的文件。不要手动修改任何代码！

---

## 深入学习
* [Maven官方文档](https://maven.apache.org/guides/getting-started/index.html)
* [The Art of UNIX Programming](http://www.faqs.org/docs/artu/) 看第一章，介绍了非常经典的UNIX设计原则。[中文摘要
[UNIX编程艺术中的17点编程原则](https://blog.csdn.net/v_july_v/article/details/6136261) 
* [The Rise of Worse is Better译文](https://zhuanlan.zhihu.com/p/20827060) 一个有趣的观点，说明为什么简单粗暴的UNIX哲学战胜了优雅的学院派

---

* [重构/Refactoring](https://book.douban.com/subject/4262627/) 这本书是以前是很经典，主要是因为它完整的提出了重构的方法论。但是我认为书中占据大部分篇章的重构步骤已经过时了，现在的IDE可以非常方便的完成大部分的重构。可以看看第三章*代码的坏味道*，了解一下什么样的代码是不好的。
* [代码大全/Code Complete](https://book.douban.com/subject/1477390/) 大部头的经典之作，第24章*重构*很好的总结相关的内容。
* [代码整洁之道/Clean Code](https://book.douban.com/subject/4199741/) 对代码规范说的比较详细，推荐阅读。
* [编写可读代码的艺术/The Art of Readable Code](https://book.douban.com/subject/10797189/) 和上面的Clean Code内容接近。
* [Google Style Guides](https://google.github.io/styleguide/) Google的代码规范，其中的C++规范比较有意思，对语言使用做了非常多的限制。
* [阿里巴巴Java开发手册](https://github.com/alibaba/p3c) 比较实用的开发手册，大部分条目也会作为我们公司的代码规范。我们对部分规范有一些[变动](https://wiki.zhenguanyu.com/fankai/AliCodeStyle#preview)



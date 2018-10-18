www.yuanfudao.com

# 数据系统基础

---

## 先修要求
* 对数据系统有基本了解
* 使用过至少一类数据系统
* 有web server开发经验

---

## 学习目标
* 了解数据系统的设计目标
* 了解数据存储模型
* 了解数据基本的存储和索引模式
* 了解常见的数据系统
* 熟悉redis的常用操作和基本原理

---

## 阅读材料
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第1章P11-13，第2章第1、2节, 第3章第1节 [4h]
* 这个翻译版还不错，可以参考 [DDIA-CN](https://vonng.gitbooks.io/ddia-cn/content/)
* [Redis设计与实现 第一版](http://redisbook.readthedocs.io/en/latest/) 第五部分第一节 数据库 [1h]

---

## 讨论大纲
### 可靠性、可扩展性、可维护性
* 基本概念
* 可靠性
	* 提高可靠性的常用方法
	* 是否一直以最高可靠性为开发目标？
* 可扩展性
	* Twitter 案例讨论
	* 如何描述负载、性能
	* 响应时间avg min max  mean p99
	* 了解SLI、SLO、SLA http://www.yunweipai.com/archives/10703.html
	* 应对负载的方法

### 数据模型与查询语言
* 基本概念：关系模型、文档模型、图数据模型、NoSQL
* 文档模型
	* linkedIn 案例讨论
	* 文档模型只能保存单一的文档，不能处理相对关系？
	* 文档模型是完全 schemaless 吗？
	* 文档模型能做的关系模型也能做？那文档模型的优势是什么？
* 数据查询语言
	* 声明式 VS 命令式
	* MapReduce查询

### 存储与检索
* 驱动数据库的数据结构
	* hash索引
		* 追加日志、分段压缩、删除记录
		* 追加日志 VS 更新日志
	* SSTables和LSM树
		* 合并和压缩的过程
		* 如何崩溃恢复
	* B 树
		* 为什么具有 n 个键的B树总是具有 O(log n) 的深度
		* B 树的存储和查询过程
		* WAL 是什么？为什么需要WAL
	* 比较B树和LSM树

### Redis
* 数据库
	* 用到了我们上面讨论的哪些技术？数据模型、查询语言、存储、检索
	* Redis的过期键删除策略
	* 为什么说redis是单线程的？服务端单线程怎么和客户端线程池怎么配合？

---

## 练习
* 系统设计
	* 猿辅导要实现一个猿圈的功能，老师和学生可以在猿圈相互关注，发布状态。包含功能：
		* 关注、取关
		* 发布状态
		* 查看自己的状态时间线
		* 查看猿圈关注的朋友动态的时间线
	* 在 lab/DataSystemBasis/design.md 里描述自己的设计方案

---

## 深入学习
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 前三章 参考资料
* [Redis设计与实现](http://redisbook.com/)
* 学习不同数据系统的具体实现，比如MongoDb、Neo4j、HBase、ES、消息系统等

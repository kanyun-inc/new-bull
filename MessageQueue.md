www.yuanfudao.com

# 消息中间件

---

## 先修要求
* 生产者消费者模型
* 序列化
* JMS
* rpc通信、NIO

## 学习目标
* 了解常用的MQ框架
* 理解分布式消息中间件的运转流程
* 了解分布式消息中间件可靠性报保障
* 了解分布式消息中间件高吞吐量的实现


## 阅读材料
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第11章 [2.5h]
* [linkedin the-log](https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-datas-unifying) Part 1、2 [1h]
* [kafka-doc](http://kafka.apache.org/0100/documentation.html) / [kafka中文文档](http://kafka.apachecn.org/documentation.html) Part 4、5、6.1 [2h]
* [RabbitMQ in Action](https://book.douban.com/subject/6000169/) 第二章 [1h]


## 讨论大纲
### 基本概念
* 消息中间件/消息队列
* 松耦合设计、事件驱动架构、流式处理
* 推模式/拉模式
* 吞吐量、负载均衡、持久化、可扩展

### 应用场景
* 场景
	* 消息没有收到
	* 我想消息7天内有效，超时清除
	* 我想业务处理完毕之后，在回ack
	* 我想发送一个45分钟后投递的消息
	* 我的消息没人消费了
	* ...

	* 异步数据交互
	* 行为跟踪
	* 日志收集
	* 流处理
	
	
* 主流消息中间件

	* activeMQ
	* rabbitMQ
	* kafka
	* rocketMQ

* 消息中间件作用

	* 解耦
	* 持久化
	* 削峰
	* 缓冲
	* 可恢复
	* 可扩展
	* 异步通信
	* 顺序性

### producer
#### 序列化
* protobuf
* json
* thrift
* avro
* 自定义

#### 发布消息
* 路由
* 消息提交
* 同步发送
* 异步发送
* 批量发送
* 顺序性
* 超时机制
* 重试机制

### broker
* 选举

	* 脑裂
* 复制

* 物理存储

	* 文件
	* 索引
	* 顺序写
	* 随机读

	
* 消息队列

	* 内存
	* 磁盘
	* 惰性队列


### consumer
* 消费组
* 轮询/长轮询
* offset
	* 自动提交
	* 同步提交
	* 异步提交
	  
	  * 幂等
	* 组合提交
* 再均衡
* 反序列化
* 消息去重

	
## 练习
* 实现自定义序列化、反序列化
* 基于内存队列实现一套JVM内的消息队列（分别实现生产者、发布者和队列）

	尽量考虑（支持）以下条件：
  * 高吞吐
  * 线程安全
  * 批处理
  * 有序队列
  * 优先级队列
  * 可运维
  * 数据尽可能不丢失



## 深入学习

* [apache kafka](http://kafka.apache.org/documentation/)
* [rabbitmq](http://www.rabbitmq.com/documentation.html)
* [RabbitMQ in Action](https://book.douban.com/subject/6000169/)
* [apache rocketmq](https://rocketmq.apache.org/docs/quick-start/)
* [Kafka权威指南](https://book.douban.com/subject/27665114/) 第3~6章



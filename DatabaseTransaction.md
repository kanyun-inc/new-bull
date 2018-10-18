www.yuanfudao.com

# 数据库事务的概念和实现

---

## 先修要求
* 了解数据系统的基本概念
* 使用过关系型数据库

---

## 学习目标
* 理解数据库的ACID特性
* 理解数据库各个隔离级别和实现方式
* 了解Redis里事务的实现
* 了解MySQL里事务和锁的实现

---

## 阅读材料
* 必看
	* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第七章 [5h]
	* 参考翻译版 [DDIA-CN](https://vonng.gitbooks.io/ddia-cn/content/)
* 选看
	* [Redis设计与实现 第一版](http://origin.redisbook.com/feature/transaction.html) 事务 这章 [0.5h]
	* [MySQL官方手册](https://dev.mysql.com/doc/refman/5.7/en/innodb-locking-transaction-model.html) MySQL 官方手册 “InnoDB 锁和事务模型” [2h]

---

## 讨论大纲
### 基本概念
* ACID 的含义
* 单对象和多对象操作

### 隔离级别
#### 弱隔离级别
* 读已提交（Read Committed）
* 快照隔离和可重复读
* 防止丢失更新
* 写入偏差与幻读

#### 可序列化
* 串行执行
* 两阶段锁定（2PL）
* 序列化快照隔离（SSI Serializable Snapshot Isolation）

### Redis 事务处理
* Redis 事务的实现
* Redis Lua 脚本的执行是否是原子的？
* Redis 对 ACID 的支持

### MySQL 事务处理
* 隔离级别
	* 几种隔离级别
	* 为什么 RR 为默认隔离级别
	* MySQL的 RR 完美的解决了幻读问题吗？如果解决了幻读，还需要SERIALIZABLE？
* 锁
	* 锁的类型
	* CRUD 加什么锁
	* 每种隔离级别加什么锁
	* 死锁
* MVCC
* MySQL 事务的实现

---

## 练习
* 在 MySQL 构造以下场景，并描述SQL过程和原因（在 lab/Database/solution.md 里补充自己的答案，有个示例）
<pre name="code" class="java">
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8
</pre>

	* 在 READ-UNCOMMITTED 隔离级别下，构造脏读
	* 在 READ-COMMITTED 隔离级别下，构造不可重复读
	* 在 REPEATBLE-READ 隔离级别下，构造幻读
	* 在 SERIALIZABLE 隔离级别下，解决幻读
	* 举出3个死锁的例子

---

## 深入学习
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第8、9章
* [MySQL官方手册](https://dev.mysql.com/doc/refman/5.7/en/innodb-storage-engine.html) MySQL 官方手册 “InnoDB 存储引擎”
* [MySQL技术内幕](https://book.douban.com/subject/24708143/) 第6章 锁，第7章 事务

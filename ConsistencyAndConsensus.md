www.yuanfudao.com

# 一致性和共识算法

---

## 先修要求

---

## 学习目标
* 了解一致性级别
* 了解常用的共识算法

---

## 阅读材料
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第九章 [5h]

---

## 讨论大纲

---

### 介绍分布式系统实践30年时间内最常用Consistency Model

* 前文回顾, SystemModel.consistencyGuarantee的意义

#### systemModel.guarantee.consistency.Linearizability

---
* Linearizability定义
    * informal 定义
    * 读不可flash back
* 使用Linearizability保证正确性
    * Locking和选主
    * 保证约束与唯一性
    * Cross-Channel方式对timing有依赖
* 实现Linearizable系统
    * 常见复制方式是否能实现linearizable system
    * 解析最难理解场景, leaderless replication下的Quorum不能实现Linearizable原因
* Linearizability的代价
    * 先批评一下CAP
    * Linearizability 与 SystemModel.fault.networkPartition
    * Linearizability 与 SystemModel.communicationLink.networkDelay

### systemModel.guarantee.ordering

* Ordering 与 Causality
    * 场景概述Causality
    * Causal Order是偏序
    * 比较Linearizable Consistency与 Causal Consistency
* 捕捉 Causality
    * Sequence Number Ordering
    * Lamport timestamps 与其局限性(Uniqueness Problem)
* Total Order Broadcast
    * 定义
    * Linearizability 与 Total-Order-Broadcast

### 分布式事务与Consensus
* Atomic Commit 与 2PC
    * 实现
    * 问题
    * 变种3PC
* 实践中的分布式事务
    * Exactly-once 消息处理
    * XA
* Consensus
    * 定义问题
    * Consensus与total-order-broadcast的关系
    * Consensus与single-leader replication的关系
    * Consensus的局限性
* 存活检测 与 协调服务
        
---
    
---

## 练习

---

## 深入学习
* [The Raft Consensus Algorithm](https://raft.github.io/)
* [Paxos](https://en.wikipedia.org/wiki/Paxos_(computer_science))
* [区块链共识算法-POW](https://www.jianshu.com/p/b23cbafbbad2)
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) P477~P479: XA V.S. LogBased
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) P492~P494: XA V.S. LogBased
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) P501~P503: XA V.S. LogBased

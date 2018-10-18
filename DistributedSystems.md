www.yuanfudao.com

# 分布式系统的基本问题

---

## 先修要求

---

## 学习目标

* 了解分布式系统面临的基本问题
* 了解分布式系统的模型和假设构建
* 了解数据复制
* 了解数据分区

---

## 阅读材料
* [Distributed System For fun and Profit](http://book.mixu.net/distsys/) 第一、二章 [1.5h]
* [分布式系统谬论/Fallacies of distributed computing](https://en.wikipedia.org/wiki/Fallacies_of_distributed_computing) [10min]
* [设计数据密集型应用/Designing Data-Intensive Applications](https://book.douban.com/subject/27154352/) 第五、六 [3.5h]

---

## 讨论大纲

---

### 广义的分布式系统

---

### 计算机科学中的分布式系统

---

### CS 分布式系统带来的优势
 * scalability
    * performance
    * availability
 * performance

---

### CS 分布式系统带来的问题
 * number of nodes
 * distance between nodes
 * 可理解性
 
---

### 为了处理可理解性引入的Abstraction 和 Model

---

### 分布式系统中我们拥有的武器
 * 思想：divide and conquer
 * Partition
 * Replication

---

### 分布式系统抽象与建模详解
 * System Model
    * node
    * communication link
    * over-all assumption
    * guarantee
    * Case Study: Consensus Problem, and impossible results
---

### 常见的Partition技术
 * Partition Of Key-Value Data
 * Partition Of Secondary Indexes
 * Rebalancing Partitions
 * Request Routing
---

### 常见的Replication技术
 * Single Leader
 * Multi Leader
 * Leaderless
 * Problems of Replication Lag
---

---

## 练习
 
 * 实现一致性Hash算法
 * 设计Dynamic Partition, 过程中保证re-balance和写新数据都成功的方法，且保证可以读到新旧数据
 
---

## 深入学习

### Fault Tolerance

[Raft](https://raft.github.io/raft.pdf)

[Paxos](http://www.the-paper-trail.org/post/2009-02-03-consensus-protocols-paxos/)

[Dynamo Paper](http://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf)

[Life beyond Distributed Transaction](http://www.ics.uci.edu/~cs223/papers/cidr07p15.pdf)

---

### Basic Primitives

[quorum](https://ecommons.library.cornell.edu/bitstream/1813/6323/1/82-483.pdf)

### 综合
[Design Data Intensive Application](https://book.douban.com/subject/26197294/) Distributed Data与Derive Data，及其中感兴趣章节的引文
www.yuanfudao.com

# 大数据处理

---

## 先修要求
* 分布式系统基础知识
* 数据库基础知识

---

## 学习目标
* 了解GFS和MapReduce
* 了解Hadoop项目
* 了解Spark计算模型

---

## 阅读材料

* [The Google File System](https://ai.google/research/pubs/pub51) 分布式文件系统，我们重点看第1、2章，第3章的第1、2节，第4章的2、3节，第5章的第1节[2h]
* [分布式键值存储 Dynamo 的实现原理](https://draveness.me/dynamo) [2h]
* [MapReduce: Simplified Data Processing on Large Clusters](https://ai.google/research/pubs/pub62) 大规模分布式计算系统，我们重点看前4章 [2h]

---

## 讨论大纲

---
### GFS

* 设计目标
    * 普通硬件组成的大规模系统中，故障是常态
    * 文件数量不是特别多 ( vs FB Haystack)
    * 大文件，追加操作和流式读取为主
    * 追求高吞吐而不是低延迟
* 架构
    * 组件 - master, chunkserver, client
    * 文件由固定长度的chunk组成，一般是64M
    * 没有缓存

--- 
### GFS Master
* master内存中维护所有的元数据, 和chunkserver通过心跳来通信
* 单点故障？
* 数据量
* 请求压力
* 数据如何保证安全？

<img src="http://2.bp.blogspot.com/-C7Qcn2akF7E/U0zVjII34hI/AAAAAAAAAQY/7Cvy2OX9m9s/s1600/GFS+architecture.JPG">

---
### GFS 数据
* 主备份(primary)和租约机制(lease)
* 数据流和控制流
* 一致性如何保证？
* atomic record append
* 版本号与垃圾回收

<img src="https://dirtysalt.github.io/html/images/gfs-write-control-and-data-flow.png">

---
### Dynamo

* 分布式KV存储
* 无主架构
* 一致性哈希
* Gossip协议

<img src="https://img.draveness.me/2017-10-24-gossip-protocol.png">

---
### Dynamo 一致性

* Quorum一致性: R + W > N
* 向量时钟
* 处理冲突数据

<img src="https://img.draveness.me/2017-10-24-version-evolution-in-dynamo.png">

---
### Dynamo 副本

* 比较副本间的数据:Merkle Tree

<img src="https://img.draveness.me/2017-10-24-merkle-hash-tree.png">

---

### MapReduce

* MapReduce之前的分布式计算
* 使用Map操作和Reduce操作来定义计算

---

### MapReduce 工作流程

<img src="https://www.researchgate.net/profile/Alain_Abran/publication/221236352/figure/fig2/AS:305649960407042@1449884037420/The-MapReduce-execution-flow-in-Googles-implementation-7.png">

---

### MapReduce

* 故障恢复
* 自定义Partition函数
* Combiner函数
* 实例
    * Word Count
    * Sort
    * Page Rank

---
### Spark

* 使用DAG来定义计算
* 中间数据不需要写磁盘
* Lazy执行
* 如何容错？
<img src="https://image.slidesharecdn.com/datascientistorg0917-150918043822-lva1-app6892/95/talk-about-hivemall-at-data-scientist-organization-on-20150917-6-638.jpg?cb=1442553224">

---

## 练习

* 思考GFS提供了怎么样的一致性模型

---

## 深入学习
* [Bigtable: A Distributed Storage System for Structured Data](https://ai.google/research/pubs/pub27898) Google的Bigtable数据库，HBase的原型
* [Dynamo: Amazon’s Highly Available Key-value Store](https://www.allthingsdistributed.com/files/amazon-dynamo-sosp2007.pdf) 亚马逊的Dynamo存储系统
* [Finding a needle in Haystack: Facebook's photo storage](https://www.usenix.org/event/osdi10/tech/full_papers/Beaver.pdf) Facebook的Haystack文件系统，用于存储海量小文件
* [知乎：分布式系统领域有哪些经典论文？](https://www.zhihu.com/question/30026369)

www.yuanfudao.com

# Java并发编程

## 预习

 * 写对并发代码的挑战是什么? 大家遇到过什么典型的坑? 或心得?
 * 了解 CAS(CompareAndSwap),  Java 对CAS的相关支持,  了解sun.misc.Unsafe
 *  讨论: 常见的互斥机制(简单理解为锁) 有哪些? 以及 Java的相关支持.
 * 什么是可见性问题? 可见性问题存在的原因?
     * 了解内存屏障的概念
     * 了解Java内存模型， volatile 关键字
 * 了解 ThreadPoolExecutor 的运行机制
     * 构造函数各个参数的含义
     * ThreadPoolExecutor 提交一个task 的逻辑过程.  上面提到的参数如何影响这个过程.
  * 了解 Java7 引入的 ForkJoinPool, 以及其和 Java8 引入 的ParallelStream是怎么协作的.


---

## 讨论大纲
---

### 基本概念
 * 并发与并行
 * 讨论: 写对并发代码的挑战

---

### 几个核心概念
 * 原子操作 : 并发场景的基本需求， 也是互斥、同步 实现的前提
 * 互斥 : 并发场景的基本需求
 * 同步 : 并发实体之间的通讯
 * 可见性 : 解决底层并发性能优化带来的副作用

---

### 原子操作
 * CAS, CompareAndSwap
 * Java 原子操作的支持

---

### 互斥
 * 常见的互斥机制
 * Java 互斥机制的相关实现

---

### 同步
 * Object .wait() .notify()
 * Lock.condition
 * Future
 * 一些Helper类:  Semaphore, CountDownLatch 等

---

### 可见性
 * 可见性问题存在的原因
 * volatile. 内存屏障. Java 内存模型

---

### Concurrent Collection

---

### Java 线程池实现 ThreadPoolExecutor
 * 运行机制剖析
 * 使用原则

---

### ForkjoinPool 框架及 ParallelStream

---

### 其它
 * ThreadLocal

---

## 深入学习
* [Java并发编程实战](https://book.douban.com/subject/10484692/)

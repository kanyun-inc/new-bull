www.yuanfudao.com

# 深入并发编程

---

## 课前阅读

 * 并发编程的相关底层实现， 可以先大概了解一些概念:
     * NUMA, SMP. [https://www.cnblogs.com/yubo/archive/2010/04/23/1718810.html]
         * SMP-Safe : 如果一个软件可以在SMP结构的计算机上正常运行，就称为“SMP Safe”.
     * 内存缓存一致性算法 MESI: [http://coderplay.iteye.com/blog/1486649] 里面关于MESI部分
 * 了解 Server 3.x 的异步Servlet, 写一写 Demo代码增进理解. [http://www.cnblogs.com/davenkin/p/async-servlet.html]
 * 了解一下我们常用的 Request-per-Thread 并发模式的缺点
 * 了解Actor 模式.
     * 没找到特别好的 简洁描述核心思想 的文档, 网上很多在讨论 AKKA. AKKA 可以简单了解下， 但AKKA是个很重的框架.
     * 这篇文章可以看一眼 : [http://www.10tiao.com/html/151/201801/2665514263/1.html]
     * 可以看看公司的一个库 commons-actor : [https://wiki.zhenguanyu.com/Projects/CommonsActor]
 *  协程
     * 尝试读懂这段Lua代码， 课上我们一起分析一下. [https://my.oschina.net/wangxuanyihaha/blog/186401]
     * 了解什么是 "用户态线程"
         * [https://c9x.me/articles/gthreads/code0.html](https://c9x.me/articles/gthreads/code0.html) 这篇文章介绍了一个简单的用户态线程的实现代码,  建议尝试读一读.  核心是理解 用户态栈 的实现. 

---

## 讨论大纲
---

### 相关的底层原理

 * 涉及到的一些底层概念:
   *  SMP (Symmetric Multi Processing) 对称多处理器架构, 与 SMP-Safe
       * CPU 缓存一致性. MESI 算法.
   * Interrupt (中断), 与 Interrupt-Safe 
   * CPU 抢占式调度(preempt), 与 Preempt-Safe
   * Context Switch 的过程， 及其开销
   * 指令集并发、重排
 * 上节课提到的， 并发编程4个基本概念的底层实现:
   *  原子操作
   * 互斥
   * 同步
   * 可见性

---

### 异步编程模型
 * Request-per-Thread 模式， 及其问题.
 * 了解异步的模式. 以 Servlet 3.x 异步Servlet 为例， 写写demo代码.
 * 异步的问题?  vs Request-per-Thread
 
---

### Actor 模式
 * 什么是Actor模式? 解决什么问题?
 * Actor的问题， 及适用场景
 * commons-actor : [https://wiki.zhenguanyu.com/Projects/CommonsActor]

---

### 协程
 * 什么是协程.
 * 一些语言选择用协程而不是线程的动机.
 * 协程的底层实现原理.


---

## 深入学习
* [深入理解计算机系统](https://book.douban.com/subject/1230413/)
    * 第3章 程序的机器级表示
    * 第4章 处理器体系结构
    * 第13章 并发编程

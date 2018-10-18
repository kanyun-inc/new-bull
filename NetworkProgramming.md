www.yuanfudao.com

# 网络编程

---

## 学习目标
* 了解基本的Socket编程
* 了解Java的NIO
* 了解网络编程的IO模型
* 了解网络编程的线程模型
* 了解公司的RPC框架

---

## 课前阅读

 * <Unix网络编程>
   * 第二章 : 传输层
    * TCP, UDP
    * TCP 的状态机. 2.7节 TIME_WAIT
  * 第七章 :  套接字选项
    * SO_KEEPALIVE
    * SO_LINGER
    * SO_REUSEADDR SO_REUSEPORT
  * 第四章: 基本TCP 套接字编程
    * 4.7 fork和exec函数
    * 4.8 并发服务器
  * 第六章: IO复用
    * 6.2 IO模型


 * Java NIO的基本概念, ByteBuf, Channel, Selector. 这部分网上资料较多.

 * <Scalable IO in Java> --  Doug Lea.  这有个中文笔记 http://www.cnblogs.com/luxiaoxun/archive/2015/03/11/4331110.html

---

## 课前练习
 * 实现各种IO模型及线程模型的 EchoServer
   * 见 lab/netprogramming 里的 EchoServer.java 文档
   * 通过单测 TestEchoServer.java

---

## 讨论大纲

 * 基本网络编程
  * TCP, UDP 等的回顾
  * 一些Socket 参数

 * IO模型
  * 阻塞IO
  * 非阻塞IO
  * IO复用
  * Signal IO
  * 异步 IO

 * 线程模型
  * 单线程
  * 多线程
  * 线程池
  * Reactor

 * 公司的RPC 框架相关

---

## 深入学习
 * Java 高性能网络框架 Netty

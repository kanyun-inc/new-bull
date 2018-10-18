www.yuanfudao.com

# JVM虚拟机

---

## 先修要求
* 熟悉Java语言

---

## 学习目标
* 了解JVM的概念和作用
* 了解JVM的内存管理和常用的GC算法
* 了解JVM类加载机制

---

## 阅读材料
* [深入理解Java虚拟机](https://book.douban.com/subject/24722612/) 了解JVM很好的教程。本次专题涉及前四部分的内容 
    * 第一部分简介和历史 [0.5h] 
    * 第二部分内存管理，看第2、3两章，其中3.5节*垃圾收集器*简单了解即可 [2h]
    * 第三部分执行子系统，看第7、8章 [2h]
    * 第四部分编译和优化，看第11章第2节了解JIT [0.5h]

---

## 讨论大纲
---

### JVM
* VM的概念和作用
* JVM vs Java
* 其他VM
---

### 内存数据区域
* 有哪些数据区域
* 堆和栈
* 直接内存/Direct Memory
* 对象创建过程
    * 新建对象的方式
    * 如何处理并发？
* 对象内存布局
    * 对象头 header
        * Mark Word和类型指针
    * 实例数据 instance data
    * 对齐填充 padding
---

### 垃圾收集
* 概念和作用
* 引用计数算法
* 可达性分析算法
    * GC Roots
    * 引用类型
    * 方法区需要回收吗？
* 常用收集算法
    * 标记-清除 Mark-Sweep
    * 复制算法
    * 标记-整理 Mark-Compact
    * 分代收集
        * 新生代和老年代
        * 永久代(Permanent Generation)和Metaspace
* 安全点和安全区域

---

### 垃圾收集器
* 常用收集器
    * Serial/Serial Old
    * Parallel/Parallel Old
    * Parallel Scavenge
    * CMS
    * G1
* Minor GC/Major GC/Mixed GC/Full GC
    * jstat里列出的GC指的是STW
    * gc log则是列出了所有的信息
* 垃圾收集与RAII的比较 *

---
### 类文件的结构
* 结构
    * 魔数与版本
    * 常量池
    * 访问标志
    * 类索引、父类索引与接口索引
    * 字段表
    * 方法表
___

### 类加载
* 加载过程
    * 加载
    * 连接 - 验证，准备，解析
    * 初始化
        * <clinit>()和<init>()
    * 使用
    * 卸载
* 虚拟机严格规定了初始化的场景（主动引用）
* 类加载器的双亲委派模型
* 怎么判断两个类相同
---

### 执行字节码
* 栈帧
    * 局部变量表
    * 操作数栈
    * 返回地址
* 方法调用
    * 解析调用
    * 分派调用
        * 静态 vs 动态
        * 单分派 vs 多分派
* 基于栈 vs 基于寄存器

---

### 编译
* AOT
    * 前端/优化器/后端
* Javac

---
### 运行时优化
* 解释器与编译器并存
    * 分层编译
    * Server模式和Client模式*
* 即时编译 JIT
    * 热点探测 Hot Span Detection
    * 栈上替换 On Stack Replacement
* 运行期优化

---

### 编译器 vs 解释器 vs 虚拟机

---

## 练习
* 通过lab中的gc项目来了解GC的过程
    * 理解TestGC.java的内容，尤其是理解WeakReference的作用
    * 运行testGC.sh，每次按回车键会new一个新的对象
    * 运行jps来获取pid，运行jmap -heap <pid>, 来查看各个内存区域的状态
    * 尝试修改testGC.sh或者TestGC.java，使得运行可以触发Full GC。有很多种可能的方式，重点是能够解释原因。
* 通过lab中的cls项目来了解字节码和运行
    * 通过javac *.java命令来编译生成class文件，通过java -cp . Main来运行
    * 使用javap -verbose查看生成的class文件
    * 运行run_jbe.sh来打开jbe，尝试修改字节码，让程序打印"drive plane"
    * 尝试修改字节码，让main()函数调用Car.horn()而不是Car.drive()
    * 尝试还原cls项目从字节码到运行的完整过程

---

## 深入学习
* [RednaxelaFX的豆列](https://www.douban.com/doulist/2545443/) 覆盖了涉及JVM实现的经典教程
* [Lambda表达式如何编译成字节码](https://blog.takipi.com/compiling-Lambda-expressions-scala-vs-java-8/)
* [Java Garbage Collection handbook](https://plumbr.io/java-garbage-collection-handbook) 这个系列文章对各个GC算法和日志有详细的解释
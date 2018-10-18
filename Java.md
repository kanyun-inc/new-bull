www.yuanfudao.com

# Java语言进阶

---

## 先修要求
* 对Java语言有一定的使用经验
    * 了解面向对象编程
    * 理解类、对象、构造函数等概念
    * 理解接口、继承和多态
    * 熟悉字符串、容器、IO流等常用的类

---

## 学习目标
* 掌握Java语言的常用特性，包括
    * 异常处理机制
    * 类型信息和反射机制
    * 泛型的原理和使用
    * 注解
* 了解Java 8引入的新特性
    * Lambda表达式
    * Stream API

---

## 阅读材料
* [Java编程思想(第4版)/Thinking In Java](https://book.douban.com/subject/2130190/) 经典的Java教程，可惜太老了，只覆盖到Java 6。[3h]
    * 第12章 异常
    * 第14章 类型信息，第2、3、4、8、9节可以跳过
    * 第15章 泛型前11节
    * 第20章 注解前2节
* [Java 7新特性](https://segmentfault.com/a/1190000004417830) 异常部分 [0.5]
* [Java 8实战/Java 8 In Action](https://book.douban.com/subject/26772632/) 前4章 [2h]

---

## 讨论大纲
---

### 异常
* 异常 vs 错误码
* Java异常的分类和层次
* Checked Exception vs Unchecked Exception
    * 各自有什么优缺点
* 如何保证释放资源
    * 如果在catch或者finally块里抛出异常会如何？
    * 如果在构造函数中跑出异常会如何？
    * try-with-resource *
* 什么叫suppressed exception，如果保留
* 永远不要吞异常！

---

### RTTI和反射
* 类型也是一个对象
* 获取和使用获取类型信息
    * 向下转型 downcast
    * instanceof 操作符
    * isInstance() 方法
    * typeid and typeinfo in c++ *
* 反射
* 动态代理
    * 什么是代理模式
    * 动态代理的作用

---

### 泛型
* 泛型类和泛型方法
* Java中泛型的实现方式
    * 和c++的模板有什么不同

---

### 泛型
* 泛型和继承
    * 协变 covariance
    * 逆变 contravariance
    * 不变 invariance
~~~
if a Derived is a Base, then
   a Collection<Derived> is a Collection<Base> ?
or a Collection<Base> is a Collection<Derived> ?
~~~

* 泛型数组？

---

### 泛型
* 泛型和继承
~~~
List<Object> objs = new ArrayList<Integer>();
objs.add(3);
objs.add("hello");
~~~

---

### 注解
* 注解如何发挥作用
* 各个元注解的意义
    * @Target
    * @Retention
    * @Documented
    * @Inherited

---

### Lambda表达式
* 从匿名类到Lambda表达式
    * 闭包
    * 有什么区别？
* 函数式接口
    * 函数不是一等公民(first class)
    * 常用接口

---

### Stream
* 不修改数据源
* 延迟操作 (lazy execution)
* 副作用 (side effect)
* 常用操作

---

### Optional
* Optional如何解决NPE
* Optional链式操作

---

## 练习
* 完善lab中的unittest项目，完成一个简单的测试框架
    * 定义所需要的各个注解
    * 完成run()函数，使其可以按照注解来运行测试用例，并打印每个测试用例的名字和耗时
    * 完成一个动态代理使其能够包装collection类并打印更多信息，并在测试用例中使用。

---

## 深入学习
* [Effective Java Third Edition](https://book.douban.com/subject/27047716/) 深入理解Java必读的经典
* [代码大全/Code Complete](https://book.douban.com/subject/1477390/) 第8章*防御式编程*，介绍了非常重要的错误处理技术，也深入的讨论了断言和异常


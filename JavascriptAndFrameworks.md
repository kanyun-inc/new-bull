www.yuanfudao.com

# TypeScript 和 Web 框架

---

## 先修要求

* 了解 RESTful
* 了解 HTTP 服务基本原理

---

## 学习目标

* 了解 TypeScript 语言的基础知识
* 了解 Angular 框架的基本用法

---

## 阅读材料

本次学习需要安装 NodeJS 和 TypeScript 开发环境，步骤如下:

```
brew install node
npm set registry http://npm.zhenguanyu.com/
npm login
npm login --registry=http://npm.zhenguanyu.com --scope=@fenbi
npm config set @fenbi:registry http://npm.zhenguanyu.com
```

* TypeScript 入门教程: https://ts.xcatliu.com
 * 简单过一遍基础语法就行
* Bootstrap Doc: http://getbootstrap.com/docs/4.1/getting-started/introduction/
 * 简单看看基本用法即可
* Angular Doc: https://angular.io/guide/quickstart
 * 这个要好好看
 * 第一部分 Getting Started
 * 第二部分 Tutorial
  * 可以跳过其中关于单元测试的部分，比如 karma
 * 第三部分 Fundamentals
  * Architecture
  * Components & Templates
  * Forms
    * Template-driven Forms 可以不看
  * Observable & RxJS
  * Dependency Injection
  * HttpClient

---

## 讨论大纲

* TypeScript
 * TypeScript 和 JavaScript 的关系
  * 为什么会有 TypeScript?
 * TypeScript 和 Java 的异同
  * 类型推断
  * Overloading 重载
  * 自动 null 值检测
  * Lambda 表达式
  * 单线程
* Angular 基础
 * 项目结构
 * Component / Module / Service 基本概念
  * Component / Directive 区别
  * Module.forRoot 的作用
 * Angular 依赖注入
* Angular 视图层
 * 基本模板语法
 * Component 生命周期
  * 在 ngAfterViewChecked 改变 view 值?
 * Component 组件间通讯方式
  * @Input, value vs value$
 * Pipe 管道
  * getter vs pipe
  * async 源码分析
* RxJS
 * 为什么要用 RxJS
 * RxJS 的基本组件概念，Subject / Observable / Subscriber
  * 什么时候 unsubscribe? Angular 中 unsubscribe 怎么做?
 * RxJS 的常见 operator
  * http://rxmarbles.com/
  * combineLatest
  * switchMap / flatMap
  * publishReplay
  * refCount
  * merge
  * distinct, reduce, ...
* Angular 多页面开发
 * Routing
  * 如何获取 url 参数
* 响应式 Angular
 * 传值 vs 传流
 * 按需刷新的实现
```
private updatedLessonId$: Subject<number> = new Subject<>();

private lessonId$: Observable<number> = routeParam$.switchMap(...).merge(this.updatedLessonId$);

onLessonSubmit: (lesson) => {
    this.updatedLesson$.next(lesson);
}
```
* 公司的 Angular 基础组件

---

## 练习

完成 Angular Doc 第二部分 Tutorial 的内容，完成「Tour of Heroes」的编写。

---

## 深入学习

# 学生管理系统说明文档

## 1. 项目简介

学生管理系统是一个基于 Java 和 Spring Boot 框架开发的 Web 应用程序,用
于管理学生信息。该系统采用前后端分离的架构设计 , 前端使用
HTML/CSS/JavaScript 实现,后端使用 Java 编写。

## 2. 功能介绍

基本功能：

1. 学生信息管理

* 添加新的学生信息
* 修改现有学生的信息
* 删除学生信息
* 查询学生信息

2. 用户管理

* 用户登录
* 用户注册

## 3. 使用说明

### 后端部署

1. 打包项目为jar包

* 进入项目根目录，执行以下命令：

```shell
mvn clean package
```

2. 配置mysql数据库

```shell
mysql -u root -p
create database student_manage;
source student_manage.sql;
```

3. 启动redis

```shell
redis-server
```

4. 启动项目

```shell
java -jar target/StudentManage-0.0.1-SNAPSHOT.jar
```

### 前端部署

在前端项目文件夹开启web服务器即可，多种方式如下：

* windows的 IIS 服务器
* Apache 服务器
* Nginx 服务器
* python 自带的 http.server 模块
* node.js 的 http-server 模块

### 浏览器访问

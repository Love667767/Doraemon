# Doraemon
哆啦A梦  采用组件化开发


##目录

  1. 组件的拆分
  2. ButterKnife的配置
  3. lambda表达式的配置
  

##一、项目组件拆分

###1.1 基础组件

- BaseCore是Framework层
    - Http请求库
    - 工具类
    - 基类
- CommonBundle是业务Lib
    - 通用的基类
    - 共通的逻辑
    - 共同的资源
  
###1.2 业务组件

- 电影组件
    - 电影列表
    - 电影搜索
    - 电影详情
    - 演员信息
    - 短评
    
- 图书组件
    - 图书分类列表
    - 图书详情
    - 图书搜索
    
- 段子组件
    - 段子列表
    - 段子详情和评论
    
- Main组件
    - Main页面
    - 设置页
    
- 登录组件
    - 闪屏页
    - 引导页
    - 登录页
    
- 通用组件
    - 分享
    - 消息推送
    - 支付



---
<br>
---

##二、ButterKnife 的配置

###2.1 关于 ButterKnife 的配置问题

ButterKnife在 `Application` 中使用 `R.id.xxx` ，在 `Library` 中使用 `R2.id.xxx` 来实现 `View` 的注入。
而组件通过如下配置实现 `Application` 和 `Library` 之间的切换。

```gradle
def isModule = isModule.toBoolean()
if (isModule) {
    apply plugin: 'com.android.application'//单独打包测试
} else {
    apply plugin: 'com.android.library'//集成测试的功能
}
```
所以这种方式下的组件库就不能使用 `ButterKnife` 了，因为当切换配置时，R和R2需要手动切换。


###2.2 解决方案

将每个组件都当做一个App工程，并让组件作为 `Library`进行开发 ，并创建一个壳App作为入口。
1. 当组件单独测试时，使用壳App进行打包测试；
2. 当作为组件时，将组件Lib生成的aar包集成到集成包的libs目录下即可；

###2.3 结构示意图

- 组件ProjectA
    - ApplicationA(壳)
    - BundleA(组件A)
    
- 组件ProjectB
    - ApplicationB(壳)
    - BundleB(组件B)
    
- 组件ProjectC
    - ApplicationC(壳)
    - BundleC(组件C)
    
- 集成打包测试(Host)
    - Applcation(Host壳)
        - libs(目录)
            - BundleA.aar
            - BundleB.aar
            - BundleC.aar
        - src
            - Application


<br>
---



##问题思考
1. 组件如何拆
2. 推送模块是单独做成一个基础组件？还是其它？清单文件的配置是否会有问题？

=================================================




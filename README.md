# Doraemon
哆啦A梦  采用组件化开发

==============================================

组件拆分：

1. 基础组件
    - Http请求库
    - BaseCore基类库
  
2. 业务组件
    - 电影组件
    - 图书组件
    - 段子组件
    - Main组件
    - 登录组件
    - 通用组件
        - 分享
        - 消息推送
        - 支付

================================================

###关于 ButterKnife 的配置问题：
ButterKnife在 `Application` 中使用 `R` ，在 `Library` 中使用 `R2` 来实现 `View` 的注入。
而组件通过配置实现 `Application` 和 `Library` 之间的切换，从而实现组件的单独打包测试和集成测试的功能，因而组件不能使用R2替换R来实现。
组件gradle的配置如下：
```gradle
def isModule = isModule.toBoolean()
if (isModule) {
    apply plugin: 'com.android.application'
} else {
    apply plugin: 'com.android.library'
}
```

####解决方案
将每个组件都当做一个App工程，并让组件作为 `Library` ，单独测试的时候，给这个组件添加一个壳App作为入口即可。

####结构示意图
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
            - BundleA
            - BundleB
            - BundleC


================================================


思考的问题：
1. 欢迎页和登陆页是放在Main组件中，还是App组件(壳组件)中？
2. 推送模块是单独做成一个基础组件？还是其它？清单文件的配置是否会有问题？

=================================================

Gradle的配置
1. gradle设置retrolambda表达式的配置
2. ButterKnife的配置
3. 组件的配置


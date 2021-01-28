# MiraiTemplate
一个Mirai console插件的Java模板

## 插件加载方式
当mcl运行时，首先会按顺序依次运行每个插件的`onLoad()`方法，但是如果有插件间的依赖关系，例如A插件依赖B插件，会先跳过A插件，直到运行完B插件的`onLoad()`方法后再运行A插件的`onLoad()`方法。  
在运行完所有插件的`onLoad()`方法后，会依次运行每个插件的`onEnable()`方法。  
在此之后，进行机器人的登录。

所以，如果在`onEnable()`中直接写让机器人发送一条消息的语句，是会报错的——因为运行`onEnable()`方法时，还没有任何机器人登录。

*   如果自己编写Mirai-Console启动器，而不使用官方提供的mcl，或许可以通过更改加载顺序使之正确运行。（我没试过）
    
*   如果一定希望机器人主动发送消息（而不是`监听到事件`[官方文档：Events](https://github.com/mamoe/mirai/blob/dev/docs/Events.md) 后发送消息），可以新开一个线程，等待一段时间后再执行语句。（在主线程里等待也是没有用的）

## 插件编写
参考mirai-core API 文档:[官方开发文档](https://github.com/mamoe/mirai/blob/dev/docs/README.md#mirai-core-api-%E6%96%87%E6%A1%A3)

## 插件打包发布
要求将除了mirai相关组件之外的全部依赖、资源文件打包  
如果build.gradle中添加了

```java
plugins {
    id 'net.mamoe.mirai-console' version '2.0.0'
}
```

那么在gradle中就可以 Tasks > mirai > build 直接将依赖文件一同打包

例如我想添加本地依赖，就在dependencies中加入

```java
    compile fileTree(dir:'lib',includes:['*jar'])
```

然后buildPlugin就会帮我把这些依赖一起打包。  
打包好的jar文件在 build\\mirai 文件夹下

把这个文件放到mcl的plugins文件夹下就好了